package com.health.care.lab.appointment.service.impl;

import static com.health.care.lab.appointment.util.CommonUtil.generateRandomNumber;

import com.health.care.lab.appointment.dto.AppointmentDto;
import com.health.care.lab.appointment.dto.DoctorDto;
import com.health.care.lab.appointment.dto.PatientDto;
import com.health.care.lab.appointment.dto.TechnicianDto;
import com.health.care.lab.appointment.entity.DoctorAppointment;
import com.health.care.lab.appointment.entity.Doctor;
import com.health.care.lab.appointment.entity.Patient;
import com.health.care.lab.appointment.entity.Technician;
import com.health.care.lab.appointment.enums.StatusType;
import com.health.care.lab.appointment.repository.DoctorAppointmentRepository;
import com.health.care.lab.appointment.service.DoctorAppointmentService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Service
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {

  @Autowired
  private DoctorAppointmentRepository doctorAppointmentRepository;

  @Override
  public void saveAndUpdateAppointment(AppointmentDto appointmentDto) {

    DoctorAppointment doctorAppointment = setAppointmentDtoData(appointmentDto);
    if (!ObjectUtils.isEmpty(doctorAppointment)){
      if (appointmentDto.getId() == null){
        doctorAppointment.setAppointmentId(generateRandomNumber("DOC_APP"));
        doctorAppointment.setStatus(StatusType.ACTIVE);
        doctorAppointmentRepository.save(doctorAppointment);
      }else {
        doctorAppointment.setStatus(StatusType.INACTIVE);
        doctorAppointment.setUpdatedDate(LocalDateTime.now());
        doctorAppointmentRepository.save(doctorAppointment);
      }
    }


  }

  @Override
  public AppointmentDto getAppointment(String name, String nic, String AppointmentId) {
    return null;
  }

  @Override
  public List<AppointmentDto> getAllAppointment() {

    List<DoctorAppointment> doctorAppointmentList = doctorAppointmentRepository.findAll();

    List<AppointmentDto> appointmentDtoList = new ArrayList<>();

    if (!CollectionUtils.isEmpty(doctorAppointmentList)){
      for (DoctorAppointment doctorAppointment : doctorAppointmentList) {
        appointmentDtoList.add(setAppointmentEntityData(doctorAppointment));
      }
    }

    return appointmentDtoList;
  }

  @Override
  public void deleteAppointment(Long id) {

    DoctorAppointment doctorAppointment = doctorAppointmentRepository.findById(id).get();
    if (!ObjectUtils.isEmpty(doctorAppointment)){
      doctorAppointment.setStatus(StatusType.INACTIVE);
      doctorAppointment.setUpdatedDate(LocalDateTime.now());
      doctorAppointmentRepository.save(doctorAppointment);
    }
  }

  private DoctorAppointment setAppointmentDtoData(AppointmentDto appointmentDto){

    DoctorAppointment doctorAppointment = new DoctorAppointment();
    doctorAppointment.setTime(appointmentDto.getTime());
    doctorAppointment.setDate(appointmentDto.getDate());
    doctorAppointment.setCreatedDate(LocalDateTime.now());

    Patient patient = new Patient();
    patient.setId(appointmentDto.getPatient().getId());

    Doctor doctor = new Doctor();
    doctor.setId(appointmentDto.getDoctor().getId());

    doctorAppointment.setPatient(patient);
    doctorAppointment.setDoctor(doctor);


    return doctorAppointment;
  }
  private AppointmentDto setAppointmentEntityData(DoctorAppointment doctorAppointment){

    AppointmentDto appointmentDto = new AppointmentDto();
    appointmentDto.setTime(doctorAppointment.getTime());
    appointmentDto.setDate(doctorAppointment.getDate());
    appointmentDto.setStatus(doctorAppointment.getStatus());
    PatientDto patient = new PatientDto();
    patient.setPatientId(doctorAppointment.getPatient().getPatientId());

    DoctorDto doctor = new DoctorDto();
    doctor.setDoctorID(doctorAppointment.getDoctor().getDoctorID());
    doctor.setFirstName(doctorAppointment.getDoctor().getFirstName());
    appointmentDto.setPatient(patient);
    appointmentDto.setDoctor(doctor);


    return appointmentDto;
  }
}
