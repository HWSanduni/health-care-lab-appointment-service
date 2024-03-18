package com.health.care.lab.appointment.service;

import com.health.care.lab.appointment.dto.RegisterRequestDto;
import com.health.care.lab.appointment.dto.TechnicianDto;
import java.util.List;

public interface TechnicianService {

  public void saveAndUpdateTechnician(RegisterRequestDto registerRequestDto);
  public TechnicianDto getTechnician(String technicianId);

  public List<TechnicianDto> getAllTechnician();

  public void deleteTechnician(Long id);
}
