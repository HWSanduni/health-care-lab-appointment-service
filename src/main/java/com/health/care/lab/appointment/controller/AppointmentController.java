package com.health.care.lab.appointment.controller;

import com.health.care.lab.appointment.dto.AppointmentDto;
import com.health.care.lab.appointment.dto.response.LabAppointmentResponse;
import com.health.care.lab.appointment.service.AppointmentService;
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
@RequestMapping("/v1/appointment")
@CrossOrigin
public class AppointmentController {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

  @Autowired
  private AppointmentService AppointmentService;

  @PostMapping("/save-appointment")
  public ResponseEntity<LabAppointmentResponse> saveAppointment(@RequestBody AppointmentDto AppointmentDto){

    LOGGER.info("Save appointment  :request={}", AppointmentDto);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      AppointmentService.saveAndUpdateAppointment(AppointmentDto);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Appointment saved successfully");
    }catch (Exception e){
      LOGGER.error("Error  saveAppointment  : error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to save Appointment");
    }
    LOGGER.info("Save appointment  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @GetMapping("/get-appointment")
  public ResponseEntity<LabAppointmentResponse> getAppointment(@RequestParam(value = "name",required = false) String name,
      @RequestParam(value = "nic",required = false) String nic,
      @RequestParam(value = "AppointmentId",required = false) String AppointmentId) {
    LOGGER.info("Get appointment request  :name={} | nic={} | AppointmentId={}",name,nic,AppointmentId);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      AppointmentDto AppointmentDto = AppointmentService.getAppointment(name,nic,AppointmentId);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Appointment detail retrieved successfully");
      labAppointmentResponse.setData(AppointmentDto);
    }catch (Exception e){
      LOGGER.info("Error  getAppointment: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to retrieved Appointment detail");
    }
    LOGGER.info("Get appointment response  : response={}",labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @GetMapping("/get-all-Appointment")
  public ResponseEntity<LabAppointmentResponse> getAllAppointment(){
    LOGGER.info("Get all appointment request");
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      List<AppointmentDto> AppointmentDtoList = AppointmentService.getAllAppointment();
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Appointments details retrieved successfully");
      labAppointmentResponse.setData(AppointmentDtoList);
    }catch (Exception e){
      LOGGER.error("Error  getAllAppointment: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to retrieved Appointments details");
    }
    LOGGER.info("Get all appointment response  :  response={}",labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @PutMapping("/update-Appointment")
  public ResponseEntity<LabAppointmentResponse> updateAppointment(@RequestBody AppointmentDto AppointmentDto){
    LOGGER.info("Update Appointment  :request={}", AppointmentDto);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      AppointmentService.saveAndUpdateAppointment(AppointmentDto);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Appointment update successfully");
    }catch (Exception e){
      LOGGER.error("Error  updateAppointment: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to update Appointment");
    }
    LOGGER.info("Update Appointment response  : response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @DeleteMapping("/delete-Appointment")
  public ResponseEntity<LabAppointmentResponse> deleteAppointment(@RequestParam(value = "AppointmentId",required = false) Long AppointmentId){
    LOGGER.info("Delete Appointment  :AppointmentId={}", AppointmentId);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      AppointmentService.deleteAppointment(AppointmentId);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Appointment delete successfully");
    }catch (Exception e){
      LOGGER.error("Error  deleteAppointment  : error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to delete Appointment");
    }
    LOGGER.info("Delete appointment  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
}
