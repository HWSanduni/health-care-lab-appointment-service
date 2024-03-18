package com.health.care.lab.appointment.controller;

import com.health.care.lab.appointment.dto.DoctorDto;
import com.health.care.lab.appointment.dto.RegisterRequestDto;
import com.health.care.lab.appointment.dto.response.LabAppointmentResponse;
import com.health.care.lab.appointment.service.DoctorService;
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
@RequestMapping("/v1/doctor")
@CrossOrigin
public class DoctorController {

  private static final Logger LOGGER = LoggerFactory.getLogger(DoctorController.class);

      @Autowired
      private DoctorService doctorService;

      @PostMapping("/save-doctor")
      public ResponseEntity<LabAppointmentResponse> saveDoctor(@RequestBody RegisterRequestDto registerRequestDto){

        LOGGER.info("Save Doctor  :request={}", registerRequestDto);
        LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
        try {
          doctorService.saveAndUpdateDoctor(registerRequestDto);
          labAppointmentResponse.setStatus(HttpStatus.OK);
          labAppointmentResponse.setMessage("Doctor saved successfully");
        }catch (Exception e){
          LOGGER.error("Error  saveDoctor  : error={}", e.getMessage());
          labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
          labAppointmentResponse.setMessage("Failed to save doctor");
        }
        LOGGER.info("Save Doctor  response  :response={}", labAppointmentResponse);
        return ResponseEntity.ok(labAppointmentResponse);
      }
     @GetMapping("/get-doctor")
     public ResponseEntity<LabAppointmentResponse> getDoctor(@RequestParam(value = "name",required = false) String name,
         @RequestParam(value = "nic",required = false) String nic,
         @RequestParam(value = "doctorId",required = false) String doctorId) {
       LOGGER.info("Get Doctor request  :name={} | nic={} | doctorId={}",name,nic,doctorId);
       LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
       try {
         DoctorDto doctorDto = doctorService.getDoctor(name,nic,doctorId);
         labAppointmentResponse.setStatus(HttpStatus.OK);
         labAppointmentResponse.setMessage("Doctor detail retrieved successfully");
         labAppointmentResponse.setData(doctorDto);
       }catch (Exception e){
         LOGGER.info("Error  getDoctor: error={}",e.getMessage());
         labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
         labAppointmentResponse.setMessage("Failed to retrieved doctor detail");
       }
       LOGGER.info("Get Doctor response  : response={}",labAppointmentResponse);
       return ResponseEntity.ok(labAppointmentResponse);
     }
     @GetMapping("/get-all-doctor")
       public ResponseEntity<LabAppointmentResponse> getAllDoctor(){
       LOGGER.info("Get all doctor request");
       LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
       try {
         List<DoctorDto> doctorDtoList = doctorService.getAllDoctor();
         labAppointmentResponse.setStatus(HttpStatus.OK);
         labAppointmentResponse.setMessage("Doctors details retrieved successfully");
         labAppointmentResponse.setData(doctorDtoList);
       }catch (Exception e){
         LOGGER.error("Error  getAllDoctor: error={}",e.getMessage());
         labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
         labAppointmentResponse.setMessage("Failed to retrieved doctors details");
       }
       LOGGER.info("Get all doctor response  :  response={}",labAppointmentResponse);
       return ResponseEntity.ok(labAppointmentResponse);
      }
     @PutMapping("/update-doctor")
     public ResponseEntity<LabAppointmentResponse> updateDoctor(@RequestBody RegisterRequestDto registerRequestDto){
       LOGGER.info("Update Doctor  :request={}", registerRequestDto);
       LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
       try {
         doctorService.saveAndUpdateDoctor(registerRequestDto);
         labAppointmentResponse.setStatus(HttpStatus.OK);
         labAppointmentResponse.setMessage("Doctor update successfully");
       }catch (Exception e){
         LOGGER.error("Error  updateDoctor: error={}",e.getMessage());
         labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
         labAppointmentResponse.setMessage("Failed to update doctor");
       }
       LOGGER.info("Update Doctor response  : response={}", labAppointmentResponse);
       return ResponseEntity.ok(labAppointmentResponse);
     }
  @DeleteMapping("/delete-doctor")
  public ResponseEntity<LabAppointmentResponse> deleteDoctor(@RequestParam(value = "doctorId",required = false) Long doctorId){
    LOGGER.info("Delete doctor  :doctorId={}", doctorId);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      doctorService.deleteDoctor(doctorId);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Doctor delete successfully");
    }catch (Exception e){
      LOGGER.error("Error  deleteDoctor  : error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to delete doctor");
    }
    LOGGER.info("Delete doctor  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  }

