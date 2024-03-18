package com.health.care.lab.appointment.service.impl;

import static com.health.care.lab.appointment.util.CommonUtil.generateRandomNumber;

import com.health.care.lab.appointment.dto.TestDto;
import com.health.care.lab.appointment.entity.Doctor;
import com.health.care.lab.appointment.entity.Patient;
import com.health.care.lab.appointment.entity.Technician;
import com.health.care.lab.appointment.entity.Test;
import com.health.care.lab.appointment.enums.StatusType;
import com.health.care.lab.appointment.repository.TestRepository;
import com.health.care.lab.appointment.service.TestService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Service
public class TestServiceImpl implements TestService {

  @Autowired
  private TestRepository testRepository;

  @Override
  public void saveAndUpdateTest(TestDto testDto) {

    Test test = setTestDtoData(testDto);
    if (!ObjectUtils.isEmpty(test)){
      if (testDto.getId()==null){
        test.setTestId(generateRandomNumber("TES"));
        test.setStatus(StatusType.ACTIVE);
        testRepository.save(test);
      }else {
        test.setId(test.getId());
        test.setUpdatedDate(LocalDateTime.now());
        testRepository.save(test);
      }
    }
  }

  @Override
  public TestDto getTest(String testType, String testId) {
    return null;
  }

  @Override
  public List<TestDto> getAllTest() {
    List<Test> testList = testRepository.findAll();
    List<TestDto> testDtoList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(testList)){
      for (Test test:testList) {
        testDtoList.add(setTestEntityData(test));
      }
    }
    return testDtoList;
  }

  @Override
  public void deleteTest(Long id) {

    Test test = testRepository.findById(id).get();
    if (ObjectUtils.isEmpty(test)){
      test.setStatus(StatusType.INACTIVE);
      testRepository.save(test);
    }
  }
  private TestDto setTestEntityData(Test test){

    TestDto testDto = new TestDto();
    testDto.setTestId(test.getTestId());
    testDto.setTestType(test.getTestType());
    testDto.setStatus(test.getStatus());
    testDto.setCreatedDate(test.getCreatedDate());
    testDto.setUpdatedDate(test.getUpdatedDate());
    return testDto;

  }

  private Test setTestDtoData(TestDto testDto){
    Test test = new Test();
    test.setTestId(testDto.getTestId());
    test.setTestType(testDto.getTestType());
    test.setStatus(testDto.getStatus());
    test.setCreatedDate(LocalDateTime.now());
    test.setUpdatedDate(LocalDateTime.now());

    Patient patient = new Patient();
    patient.setPatientId(testDto.getPatient().getPatientId());

    Doctor doctor = new Doctor();
    doctor.setDoctorID(testDto.getDoctor().getDoctorID());

    Technician technician = new Technician();
    technician.setTechnicianId(testDto.getTechnician().getTechnicianId());

    test.setPatient(patient);
    test.setDoctor(doctor);
    test.setTechnician(technician);



    return test;
  }
}
