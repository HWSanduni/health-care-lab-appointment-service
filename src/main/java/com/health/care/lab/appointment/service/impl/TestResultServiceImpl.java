package com.health.care.lab.appointment.service.impl;

import static com.health.care.lab.appointment.util.CommonUtil.generateRandomNumber;

import com.health.care.lab.appointment.dto.DoctorDto;
import com.health.care.lab.appointment.dto.PatientDto;
import com.health.care.lab.appointment.dto.TechnicianDto;
import com.health.care.lab.appointment.dto.TestDto;
import com.health.care.lab.appointment.dto.TestResultDto;
import com.health.care.lab.appointment.entity.Doctor;
import com.health.care.lab.appointment.entity.Patient;
import com.health.care.lab.appointment.entity.Technician;
import com.health.care.lab.appointment.entity.Test;
import com.health.care.lab.appointment.entity.TestResult;
import com.health.care.lab.appointment.enums.StatusType;
import com.health.care.lab.appointment.repository.TestResultRepository;
import com.health.care.lab.appointment.service.TestResultService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Service
public class TestResultServiceImpl implements TestResultService {

  @Autowired
  private TestResultRepository testResultRepository;
  @Override
  public void saveAndUpdateTestResult(TestResultDto testResultDto) {

    TestResult testResult = setTestResultDtoData(testResultDto);

    if (!ObjectUtils.isEmpty(testResult)){
      if (testResult.getId() == null){
        testResult.setStatus(StatusType.ACTIVE);
        testResult.setTestResultId(generateRandomNumber("TESR"));
        testResult.setCreatedDate(LocalDateTime.now());
        testResultRepository.save(testResult);
      }else{
        testResult.setId(testResultDto.getId());
        testResult.setUpdatedDate(LocalDateTime.now());
        testResultRepository.save(testResult);
      }

    }


  }

  @Override
  public TestResultDto getTestResult(String testResultType, String testResultId) {
    return null;
  }

  @Override
  public List<TestResultDto> getAllTestResult() {

    List<TestResult> testResultList = testResultRepository.findAll();
    List<TestResultDto> testResultDtoList = new ArrayList<>();

    if (!CollectionUtils.isEmpty(testResultList)){
      for (TestResult testResult:testResultList) {
        testResultDtoList.add(setTestResultEntityData(testResult));
      }
    }
    return testResultDtoList;
  }

  @Override
  public void deleteTestResult(Long id) {

    TestResult testResult = testResultRepository.findById(id).get();

    if (!ObjectUtils.isEmpty(testResult)){
      testResult.setStatus(StatusType.INACTIVE);
      testResult.setUpdatedDate(LocalDateTime.now());
      testResultRepository.save(testResult);
    }

  }

  private TestResult setTestResultDtoData(TestResultDto testResultDto){
    TestResult testResult = new TestResult();
    testResult.setTestResultContent(testResultDto.getTestResultContent());

    Patient patient = new Patient();
    patient.setPatientId(testResultDto.getPatient().getPatientId());

    Doctor doctor = new Doctor();
    doctor.setDoctorID(testResultDto.getDoctor().getDoctorID());

    Technician technician = new Technician();
    technician.setTechnicianId(testResultDto.getTechnician().getTechnicianId());

    Test test = new Test();
    test.setTestId(testResultDto.getTest().getTestId());

    testResult.setPatient(patient);
    testResult.setDoctor(doctor);
    testResult.setTechnician(technician);
    testResult.setTest(test);

    return testResult;
  }

  private TestResultDto setTestResultEntityData(TestResult testResult){
    TestResultDto testResultDto = new TestResultDto();
    testResultDto.setTestResultContent(testResult.getTestResultContent());

    PatientDto patient = new PatientDto();
    patient.setPatientId(testResult.getPatient().getPatientId());

    DoctorDto doctor = new DoctorDto();
    doctor.setDoctorID(testResult.getDoctor().getDoctorID());

    TechnicianDto technician = new TechnicianDto();
    technician.setTechnicianId(testResult.getTechnician().getTechnicianId());

    TestDto test = new TestDto();
    test.setTestId(testResult.getTest().getTestId());

    testResultDto.setPatient(patient);
    testResultDto.setDoctor(doctor);
    testResultDto.setTechnician(technician);
    testResultDto.setTest(test);

    return testResultDto;
  }
}
