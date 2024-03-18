package com.health.care.lab.appointment.controller;

import com.health.care.lab.appointment.dto.PatientDto;
import com.health.care.lab.appointment.dto.RegisterRequestDto;
import com.health.care.lab.appointment.dto.response.LabAppointmentResponse;
import com.health.care.lab.appointment.service.PatientService;
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
@RequestMapping("v1/patient")
@CrossOrigin
public class PatientController {
  private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

  @Autowired
  private PatientService patientService;

  @PostMapping("/save-patient")
  public ResponseEntity<LabAppointmentResponse> savePatient(@RequestBody RegisterRequestDto registerRequestDto){
    LOGGER.info("Save patient request :request={}", registerRequestDto);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      patientService.saveAndUpdatePatient(registerRequestDto);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Patient saved successfully");
    }catch (Exception e){
      LOGGER.error("Error  savePatient  error: error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to save patient");
    }
    LOGGER.info("Save patient  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @GetMapping("/get-patient")
  public ResponseEntity<LabAppointmentResponse> getPatient
      (@RequestParam(value = "name",required = false) String name,
      @RequestParam(value = "nic",required = false) String nic,
      @RequestParam(value = "patientId",required = false) String patientId){
    return ResponseEntity.ok(null);
  }
  @GetMapping("/get-all-patient")
  public ResponseEntity<LabAppointmentResponse> getAllPatient(){
    LOGGER.info("Get all patient  request");
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      List<PatientDto>  patientDtoList=patientService.getAllPatient();
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Patients details retrieved successfully");
      labAppointmentResponse.setData(patientDtoList);
    }catch (Exception e){
      LOGGER.error("Error  getAllPatient  error: error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to retrieved patients details");
    }
    LOGGER.info("Get all  patients  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }

  @PutMapping("/update-patient")
  public ResponseEntity<LabAppointmentResponse> updatePatient(@RequestBody RegisterRequestDto registerRequestDto){
    LOGGER.info("Update patient  :request={}", registerRequestDto);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      patientService.saveAndUpdatePatient(registerRequestDto);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Patient update successfully");
    }catch (Exception e){
      LOGGER.error("Error  updatePatient  : error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to update patient");
    }
    LOGGER.info("Update patient  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @DeleteMapping("/delete-patient")
  public ResponseEntity<LabAppointmentResponse> deletePatient(@RequestParam(value = "patientId",required = false) Long patientId){
    LOGGER.info("Delete patient  :patientId={}", patientId);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      patientService.deletePatient(patientId);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Patient delete successfully");
    }catch (Exception e){
      LOGGER.error("Error  deletePatient  : error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to delete patient");
    }
    LOGGER.info("Delete patient  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
}
