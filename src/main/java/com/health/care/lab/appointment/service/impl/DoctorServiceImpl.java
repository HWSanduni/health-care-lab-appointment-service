package com.health.care.lab.appointment.service.impl;

import static com.health.care.lab.appointment.util.CommonUtil.generateRandomNumber;

import com.health.care.lab.appointment.dto.DoctorDto;
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
  public void saveAndUpdateDoctor(DoctorDto doctorDto) {
    Doctor doctor = setDoctorDtoData(doctorDto);
    if (!ObjectUtils.isEmpty(doctor)){
      if (doctorDto.getId() == null){
        doctor.setDoctorID(generateRandomNumber("DOC"));
        doctor.setStatus(StatusType.ACTIVE);
        doctorRepository.save(doctor);
      }else {
        doctor.setId(doctorDto.getId());
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

  public DoctorDto setDoctorEntityData(Doctor doctor){
    DoctorDto doctorDto = new DoctorDto();
    doctorDto.setId(doctor.getId());
    doctorDto.setName(doctor.getName());
    doctorDto.setTelNumber(doctor.getTelNumber());
    doctorDto.setNic(doctor.getNic());
    doctorDto.setEmail(doctor.getEmail());
    doctorDto.setStatus(doctor.getStatus());
    return doctorDto;
  }
  public Doctor setDoctorDtoData(DoctorDto doctorDto){
    Doctor doctor = new Doctor();
    doctor.setName(doctorDto.getName());
    doctor.setTelNumber(doctorDto.getTelNumber());
    doctor.setNic(doctorDto.getNic());
    doctor.setEmail(doctorDto.getEmail());
    return doctor;
  }
}
