package com.health.care.lab.appointment.service;

import com.health.care.lab.appointment.dto.DoctorDto;
import java.util.List;

public interface DoctorService {

  public void saveAndUpdateDoctor(DoctorDto doctorDto);
  public DoctorDto getDoctor(String name,String nic,String doctorId);
  public List<DoctorDto> getAllDoctor ();
  public void deleteDoctor(Long id);
}
