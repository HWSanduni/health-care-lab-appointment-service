package com.health.care.lab.appointment.service.impl;

import static com.health.care.lab.appointment.util.CommonUtil.generateRandomNumber;

import com.health.care.lab.appointment.dto.PatientDto;
import com.health.care.lab.appointment.entity.Patient;
import com.health.care.lab.appointment.enums.StatusType;
import com.health.care.lab.appointment.repository.PatientRepository;
import com.health.care.lab.appointment.service.PatientService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Service
public class PatientServiceImpl implements PatientService {

  @Autowired
  private PatientRepository patientRepository;

  @Override
  public void saveAndUpdatePatient(PatientDto patientDto) {

    Patient patient = setPatientDtoData(patientDto);
    if (!ObjectUtils.isEmpty(patient)){
      if (patientDto.getId() == null){

        patient.setStatus(StatusType.ACTIVE);
        patientRepository.save(patient);
      }else{
        patient.setId(patientDto.getId());
        patientRepository.save(patient);
      }

    }

  }

  @Override
  public PatientDto getPatient(String patientId) {
    return null;
  }

  @Override
  public List<PatientDto> getAllPatient() {

    List<Patient> patientList= patientRepository.findAll();
    List<PatientDto> patientDtoList = new ArrayList<>();

    if (!CollectionUtils.isEmpty(patientList)){
      for (Patient patient:patientList) {
        patientDtoList.add(setPatientEntityData(patient));
      }
    }
    return patientDtoList;
  }

  @Override
  public void deletePatient(Long id) {
    Patient patient = patientRepository.findById(id).get();

    if (patient != null){
      patient.setStatus(StatusType.INACTIVE);
      patientRepository.save(patient);
    }

  }

  private PatientDto setPatientEntityData(Patient patient){
    PatientDto patientDto = new PatientDto();
    patientDto.setId(patient.getId());
    patientDto.setPatientId(patient.getPatientId());
    patientDto.setName(patient.getName());
    patientDto.setAddress(patient.getAddress());
    patientDto.setEmail(patient.getEmail());
    patientDto.setAge(patient.getAge());
    patientDto.setTelNumber(patient.getTelNumber());
    patientDto.setGender(patient.getGender());
    patientDto.setNic(patient.getNic());
    patientDto.setStatus(patient.getStatus());
    return patientDto;
  }
  private Patient setPatientDtoData(PatientDto patientDto){
    Patient patient = new Patient();
    patient.setPatientId(patientDto.getPatientId());
    patient.setName(patientDto.getName());
    patient.setAddress(patientDto.getAddress());
    patient.setEmail(patientDto.getEmail());
    patient.setAge(patientDto.getAge());
    patient.setTelNumber(patientDto.getTelNumber());
    patient.setGender(patientDto.getGender());
    patient.setNic(patientDto.getNic());

    return patient;
  }

}
