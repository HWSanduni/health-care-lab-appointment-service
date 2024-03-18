package com.health.care.lab.appointment.service;

import com.health.care.lab.appointment.dto.AppointmentDto;
import java.util.List;

public interface DoctorAppointmentService {
  public void saveAndUpdateAppointment(AppointmentDto AppointmentDto);
  public AppointmentDto getAppointment(String name,String nic,String AppointmentId);
  public List<AppointmentDto> getAllAppointment ();
  public void deleteAppointment(Long id);
}
