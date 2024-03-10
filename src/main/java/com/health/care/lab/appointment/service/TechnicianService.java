package com.health.care.lab.appointment.service;

import com.health.care.lab.appointment.dto.TechnicianDto;
import java.util.List;

public interface TechnicianService {

  public void saveAndUpdateTechnician(TechnicianDto technicianDto);
  public TechnicianDto getTechnician(String name,String nic,String technicianId);

  public List<TechnicianDto> getAllTechnician();

  public void deleteTechnician(Long id);
}
