package com.health.care.lab.appointment.service;

import com.health.care.lab.appointment.dto.PatientDto;
import java.util.List;

public interface PatientService {
  public void saveAndUpdatePatient(PatientDto patientDto);
  public PatientDto getPatient(String patientId);
  public List<PatientDto> getAllPatient ();

  public void deletePatient(Long id);
}
