package com.health.care.lab.appointment.service.impl;

import static com.health.care.lab.appointment.util.CommonUtil.generateRandomNumber;

import com.health.care.lab.appointment.dto.TechnicianDto;
import com.health.care.lab.appointment.entity.Technician;
import com.health.care.lab.appointment.enums.StatusType;
import com.health.care.lab.appointment.repository.TechnicianRepository;
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

  @Override
  public void saveAndUpdateTechnician(TechnicianDto technicianDto) {

    Technician technician = setTechnicianDtoData(technicianDto);
    if (!ObjectUtils.isEmpty(technician)) {
      if (technicianDto.getId() == null) {
        technician.setTechnicianId(generateRandomNumber("TEC"));
        technician.setStatus(StatusType.ACTIVE);
        technicianRepository.save(technician);
      }else {
        technician.setId(technicianDto.getId());
        technicianRepository.save(technician);
      }

    }

  }

  @Override
  public TechnicianDto getTechnician(String name, String nic, String technicianId) {
    return null;
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
    technicianDto.setTechnicianId(technician.getTechnicianId());
    technicianDto.setName(technician.getName());
    technicianDto.setTelNumber(technician.getTelNumber());
    technicianDto.setEmail(technician.getEmail());
    return technicianDto;
  }

  private Technician setTechnicianDtoData(TechnicianDto technicianDto){
    Technician technician = new Technician();
    technician.setTechnicianId(technicianDto.getTechnicianId());
    technician.setName(technicianDto.getName());
    technician.setTelNumber(technicianDto.getTelNumber());
    technician.setEmail(technicianDto.getEmail());
    return technician;
  }


}
