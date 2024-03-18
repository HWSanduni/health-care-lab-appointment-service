package com.health.care.lab.appointment.service.impl;

import static com.health.care.lab.appointment.util.CommonUtil.generateRandomNumber;

import com.health.care.lab.appointment.dto.AppointmentDto;
import com.health.care.lab.appointment.dto.DoctorDto;
import com.health.care.lab.appointment.dto.PatientDto;
import com.health.care.lab.appointment.dto.RegisterRequestDto;
import com.health.care.lab.appointment.dto.TechnicianDto;
import com.health.care.lab.appointment.entity.Patient;
import com.health.care.lab.appointment.entity.Technician;
import com.health.care.lab.appointment.entity.TechnicianAppointment;
import com.health.care.lab.appointment.enums.StatusType;
import com.health.care.lab.appointment.repository.TechnicianRepository;
import com.health.care.lab.appointment.service.TechnicianAppointmentService;
import com.health.care.lab.appointment.service.TechnicianService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Service
public class TechnicianServiceImpl implements TechnicianService {

  @Autowired
  private TechnicianRepository technicianRepository;

  @Autowired
  private TechnicianAppointmentService technicianAppointmentService;

  @Override
  public void saveAndUpdateTechnician(RegisterRequestDto registerRequestDto) {

    Technician technician = setRegisterData(registerRequestDto);
    if (!ObjectUtils.isEmpty(technician)) {
      if (registerRequestDto.getId() == null) {
        technician.setTechnicianId(generateRandomNumber("TEC"));
        technician.setStatus(StatusType.ACTIVE);
        technicianRepository.save(technician);
      }else {
        technician.setId(registerRequestDto.getId());
        technicianRepository.save(technician);
      }

    }

  }

  @Override
  public TechnicianDto getTechnician(String technicianId) {
    Technician technician = technicianRepository.findByTechnicianId(technicianId);
    TechnicianDto technicianDto = setTechnicianEntityData(technician);

    List<AppointmentDto> appointmentTechnician = technicianAppointmentService.getAppointmentTechnician(
        technician.getId());

    technicianDto.setAppointments(appointmentTechnician);
    return technicianDto;
  }

  @Override
  public List<TechnicianDto> getAllTechnician() {

    List<Technician> technicianList = technicianRepository.findAll();
    List<TechnicianDto> technicianDtoList = new ArrayList<>();

    if (!CollectionUtils.isEmpty(technicianList)){
      for (Technician technician: technicianList) {
        technicianDtoList.add(setTechnicianEntityData(technician));
      }
    }

    return technicianDtoList;
  }

  @Override
  public void deleteTechnician(Long id) {
    Technician technician = technicianRepository.findById(id).get();
    if (technician != null){
      technician.setStatus(StatusType.INACTIVE);
      technicianRepository.save(technician);
    }
  }

  private TechnicianDto setTechnicianEntityData(Technician technician){
    TechnicianDto technicianDto = new TechnicianDto();
    technicianDto.setId(technician.getId());
    technicianDto.setTechnicianId(technician.getTechnicianId());
    technicianDto.setFirstName(technician.getFirstName());
    technicianDto.setLastName(technician.getLastName());
    technicianDto.setTelNumber(technician.getTelNumber());
    technicianDto.setTestType(technician.getTestType());
    technicianDto.setEmail(technician.getEmail());

    List<AppointmentDto> appointmentDtoList = new ArrayList<>();

    for (TechnicianAppointment technicianAppointment: technician.getTechnicianAppointment()) {
      System.out.println("----");
      appointmentDtoList.add(setAppointmentEntityData(technicianAppointment));
    }

    technicianDto.setAppointments(appointmentDtoList);
    return technicianDto;
  }

  private Technician setTechnicianDtoData(TechnicianDto technicianDto){
    Technician technician = new Technician();
    technician.setTechnicianId(technicianDto.getTechnicianId());
    technician.setFirstName(technicianDto.getFirstName());
    technician.setLastName(technicianDto.getLastName());
    technician.setTelNumber(technicianDto.getTelNumber());
    technician.setEmail(technicianDto.getEmail());
    return technician;
  }
  public Technician setRegisterData(RegisterRequestDto registerRequestDto){
    Technician technician = new Technician();
    technician.setTechnicianId(registerRequestDto.getUserId());
    technician.setFirstName(registerRequestDto.getFirstName());
    technician.setLastName(registerRequestDto.getLastName());
    technician.setTelNumber(registerRequestDto.getTelNumber());
    technician.setEmail(registerRequestDto.getEmail());
    technician.setTestType(registerRequestDto.getTestType());
    return technician;
  }
  private AppointmentDto setAppointmentEntityData(TechnicianAppointment technicianAppointment){

    AppointmentDto appointmentDto = new AppointmentDto();
    appointmentDto.setTime(technicianAppointment.getTime());
    appointmentDto.setDate(technicianAppointment.getDate());

    appointmentDto.setTestType(technicianAppointment.getTestType());
    appointmentDto.setStatus(technicianAppointment.getStatus());

    PatientDto patient = new PatientDto();
    patient.setPatientId(technicianAppointment.getPatient().getPatientId());
    patient.setFirstName(technicianAppointment.getPatient().getFirstName());

    DoctorDto doctor = new DoctorDto();
    doctor.setDoctorID(technicianAppointment.getDoctor().getDoctorID());

    TechnicianDto technician = new TechnicianDto();
    technician.setId(technicianAppointment.getTechnician().getId());

    technician.setFirstName(technicianAppointment.getTechnician().getFirstName());

    appointmentDto.setPatient(patient);
    appointmentDto.setDoctor(doctor);
    appointmentDto.setTechnician(technician);

    return appointmentDto;
  }
}
