package com.health.care.lab.appointment.service.impl;

import static com.health.care.lab.appointment.util.CommonUtil.generateRandomNumber;

import com.health.care.lab.appointment.dto.AppointmentDto;
import com.health.care.lab.appointment.dto.DoctorDto;
import com.health.care.lab.appointment.dto.PatientDto;
import com.health.care.lab.appointment.dto.TechnicianDto;
import com.health.care.lab.appointment.entity.Appointment;
import com.health.care.lab.appointment.entity.Doctor;
import com.health.care.lab.appointment.entity.Patient;
import com.health.care.lab.appointment.entity.Technician;
import com.health.care.lab.appointment.enums.StatusType;
import com.health.care.lab.appointment.repository.AppointmentRepository;
import com.health.care.lab.appointment.service.AppointmentService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Service
public class AppointmentServiceImpl  implements AppointmentService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Override
  public void saveAndUpdateAppointment(AppointmentDto appointmentDto) {

    Appointment appointment = setAppointmentDtoData(appointmentDto);
    if (!ObjectUtils.isEmpty(appointment)){
      if (appointmentDto.getId() == null){
        appointment.setAppointmentId(generateRandomNumber("APP"));
        appointment.setStatus(StatusType.ACTIVE);
        appointmentRepository.save(appointment);
      }else {
        appointment.setStatus(StatusType.INACTIVE);
        appointment.setUpdatedDate(LocalDateTime.now());
        appointmentRepository.save(appointment);
      }
    }


  }

  @Override
  public AppointmentDto getAppointment(String name, String nic, String AppointmentId) {
    return null;
  }

  @Override
  public List<AppointmentDto> getAllAppointment() {

    List<Appointment> appointmentList = appointmentRepository.findAll();

    List<AppointmentDto> appointmentDtoList = new ArrayList<>();

    if (!CollectionUtils.isEmpty(appointmentList)){
      for (Appointment appointment:appointmentList) {
        appointmentDtoList.add(setAppointmentEntityData(appointment));
      }
    }

    return appointmentDtoList;
  }

  @Override
  public void deleteAppointment(Long id) {

    Appointment appointment = appointmentRepository.findById(id).get();
    if (!ObjectUtils.isEmpty(appointment)){
      appointment.setStatus(StatusType.INACTIVE);
      appointment.setUpdatedDate(LocalDateTime.now());
      appointmentRepository.save(appointment);
    }
  }

  private Appointment setAppointmentDtoData(AppointmentDto appointmentDto){

    Appointment appointment = new Appointment();
    appointment.setAppointmentType(appointmentDto.getAppointmentType());
    appointment.setTime(appointmentDto.getTime());
    appointment.setDate(appointmentDto.getDate());
    appointment.setCreatedDate(LocalDateTime.now());

    Patient patient = new Patient();
    patient.setPatientId(appointmentDto.getPatient().getPatientId());

    Doctor doctor = new Doctor();
    doctor.setDoctorID(appointmentDto.getDoctor().getDoctorID());

    Technician technician = new Technician();
    technician.setTechnicianId(appointmentDto.getTechnician().getTechnicianId());

    appointment.setPatient(patient);
    appointment.setDoctor(doctor);
    appointment.setTechnician(technician);

    return appointment;
  }
  private AppointmentDto setAppointmentEntityData(Appointment appointment){

    AppointmentDto appointmentDto = new AppointmentDto();
    appointmentDto.setAppointmentType(appointment.getAppointmentType());
    appointmentDto.setTime(appointment.getTime());
    appointmentDto.setDate(appointment.getDate());

    PatientDto patient = new PatientDto();
    patient.setPatientId(appointment.getPatient().getPatientId());

    DoctorDto doctor = new DoctorDto();
    doctor.setDoctorID(appointment.getDoctor().getDoctorID());

    TechnicianDto technician = new TechnicianDto();
    technician.setTechnicianId(appointment.getTechnician().getTechnicianId());

    appointmentDto.setPatient(patient);
    appointmentDto.setDoctor(doctor);
    appointmentDto.setTechnician(technician);

    return appointmentDto;
  }
}
