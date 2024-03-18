package com.health.care.lab.appointment.controller;

import com.health.care.lab.appointment.dto.RegisterRequestDto;
import com.health.care.lab.appointment.dto.TechnicianDto;
import com.health.care.lab.appointment.dto.response.LabAppointmentResponse;
import com.health.care.lab.appointment.service.TechnicianService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/technician")
@CrossOrigin
public class TechnicianController {
  private static final Logger LOGGER = LoggerFactory.getLogger(TechnicianController.class);

  @Autowired
  private TechnicianService technicianService;

  @PostMapping("/save-technician")
  public ResponseEntity<LabAppointmentResponse> saveTechnician(@RequestBody RegisterRequestDto registerRequestDto){

    LOGGER.info("Save technician  :request={}", registerRequestDto);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      technicianService.saveAndUpdateTechnician(registerRequestDto);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Technician saved successfully");
    }catch (Exception e){
      LOGGER.error("Error  saveTechnician  : error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to save technician");
    }
    LOGGER.info("Save technician  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @GetMapping("/get-technician")
  public ResponseEntity<LabAppointmentResponse> gettechnician(@RequestParam(value = "technicianId",required = false) String technicianId) {
    LOGGER.info("Get technician request  :technicianId={}",technicianId);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      TechnicianDto technicianDto = technicianService.getTechnician(technicianId);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("technician detail retrieved successfully");
      labAppointmentResponse.setData(technicianDto);
    }catch (Exception e){
      LOGGER.info("Error  gettechnician: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to retrieved technician detail");
    }
    LOGGER.info("Get technician response  : response={}",labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @GetMapping("/get-all-technician")
  public ResponseEntity<LabAppointmentResponse> getAlltechnician(){
    LOGGER.info("Get all technician request");
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      List<TechnicianDto> technicianDtoList = technicianService.getAllTechnician();
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("technicians details retrieved successfully");
      labAppointmentResponse.setData(technicianDtoList);
    }catch (Exception e){
      LOGGER.error("Error  getAlltechnician: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to retrieved technicians details");
    }
    LOGGER.info("Get all technician response  :  response={}",labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @PutMapping("/update-technician")
  public ResponseEntity<LabAppointmentResponse> updateTechnician(@RequestBody RegisterRequestDto registerRequestDto){
    LOGGER.info("Update technician  :request={}", registerRequestDto);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      technicianService.saveAndUpdateTechnician(registerRequestDto);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("technician update successfully");
    }catch (Exception e){
      LOGGER.error("Error  updatetechnician: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to update technician");
    }
    LOGGER.info("Update technician response  : response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @DeleteMapping("/delete-technician")
  public ResponseEntity<LabAppointmentResponse> deleteTechnician(@RequestParam(value = "technicianId",required = false) Long technicianId){
    LOGGER.info("Delete technician  :technicianId={}", technicianId);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      technicianService.deleteTechnician(technicianId);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("technician delete successfully");
    }catch (Exception e){
      LOGGER.error("Error  deletetechnician  : error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to delete technician");
    }
    LOGGER.info("Delete technician  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
}

