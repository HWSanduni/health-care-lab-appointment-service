package com.health.care.lab.appointment.service.impl;

import static com.health.care.lab.appointment.util.CommonUtil.generateRandomNumber;

import com.health.care.lab.appointment.dto.DoctorDto;
import com.health.care.lab.appointment.dto.RegisterRequestDto;
import com.health.care.lab.appointment.entity.Doctor;
import com.health.care.lab.appointment.enums.StatusType;
import com.health.care.lab.appointment.repository.DoctorRepository;
import com.health.care.lab.appointment.service.DoctorService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Service
public class DoctorServiceImpl implements DoctorService {

  @Autowired
  private DoctorRepository doctorRepository;

  @Override
  public void saveAndUpdateDoctor(RegisterRequestDto registerRequestDto) {
    Doctor doctor = setRegisterData(registerRequestDto);
    if (!ObjectUtils.isEmpty(doctor)){
      if (registerRequestDto.getId() == null){
        doctor.setStatus(StatusType.ACTIVE);
        doctorRepository.save(doctor);
      }else {
        doctor.setId(registerRequestDto.getId());
        doctorRepository.save(doctor);
      }
    }
  }

  @Override
  public DoctorDto getDoctor(String name,String nic,String doctorId) {
    Doctor doctor = doctorRepository.findDoctorsByOptionalParameters(name,nic, doctorId);
    if (!ObjectUtils.isEmpty(doctor)){
      return setDoctorEntityData(doctor);
    }
    return null;
  }

  @Override
  public List<DoctorDto> getAllDoctor() {

    List<Doctor> doctorList = doctorRepository.findAll();
    List<DoctorDto> doctorDtoList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(doctorList)){
      for (Doctor doctor:doctorList) {
        doctorDtoList.add(setDoctorEntityData(doctor));
      }
    }
    return doctorDtoList;
  }

  @Override
  public void deleteDoctor(Long id) {
    Doctor doctor = doctorRepository.findById(id).get();
    if (doctor != null){
      doctor.setStatus(StatusType.INACTIVE);
      doctorRepository.save(doctor);
    }
  }

  @Override
  public void getRegisterData(RegisterRequestDto registerRequestDto) {

  }

  public DoctorDto setDoctorEntityData(Doctor doctor){
    DoctorDto doctorDto = new DoctorDto();
    doctorDto.setId(doctor.getId());
    doctorDto.setDoctorID(doctor.getDoctorID());
    doctorDto.setFirstName(doctor.getFirstName());
    doctorDto.setLastName(doctor.getLastName());
    doctorDto.setTelNumber(doctor.getTelNumber());
    doctorDto.setNic(doctor.getNic());
    doctorDto.setEmail(doctor.getEmail());
    doctorDto.setStatus(doctor.getStatus());
    return doctorDto;
  }
  public Doctor setDoctorDtoData(DoctorDto doctorDto){
    Doctor doctor = new Doctor();
    doctor.setFirstName(doctorDto.getFirstName());
    doctor.setLastName(doctorDto.getLastName());
    doctor.setTelNumber(doctorDto.getTelNumber());
    doctor.setNic(doctorDto.getNic());
    doctor.setEmail(doctorDto.getEmail());
    return doctor;
  }

  public Doctor setRegisterData(RegisterRequestDto registerRequestDto){
    Doctor doctor = new Doctor();
    doctor.setDoctorID(registerRequestDto.getUserId());
    doctor.setFirstName(registerRequestDto.getFirstName());
    doctor.setLastName(registerRequestDto.getLastName());
    doctor.setTelNumber(registerRequestDto.getTelNumber());
    doctor.setNic(registerRequestDto.getNic());
    doctor.setEmail(registerRequestDto.getEmail());
    return doctor;
  }

}
