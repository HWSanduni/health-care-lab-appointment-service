package com.health.care.lab.appointment.service;

import com.health.care.lab.appointment.dto.PatientDto;
import com.health.care.lab.appointment.dto.RegisterRequestDto;
import java.util.List;

public interface PatientService {
  public void saveAndUpdatePatient(RegisterRequestDto registerRequestDto);
  public PatientDto getPatient(String patientId);
  public List<PatientDto> getAllPatient ();

  public void deletePatient(Long id);
}
