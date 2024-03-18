package com.health.care.lab.appointment.service;

import com.health.care.lab.appointment.dto.DoctorDto;
import com.health.care.lab.appointment.dto.RegisterRequestDto;
import java.util.List;

public interface DoctorService {

  public void saveAndUpdateDoctor(RegisterRequestDto registerRequestDto);
  public DoctorDto getDoctor(String name,String nic,String doctorId);
  public List<DoctorDto> getAllDoctor ();
  public void deleteDoctor(Long id);

  public void getRegisterData(RegisterRequestDto registerRequestDto);
}
