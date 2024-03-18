package com.health.care.lab.appointment.controller;

import com.health.care.lab.appointment.dto.TestDto;
import com.health.care.lab.appointment.dto.response.LabAppointmentResponse;
import com.health.care.lab.appointment.service.TestService;
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
@RequestMapping("/v1/test")
@CrossOrigin
public class TestController {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

  @Autowired
  private TestService testService;

  @PostMapping("/save-test")
  public ResponseEntity<LabAppointmentResponse> saveTest(@RequestBody TestDto testDto){

    LOGGER.info("Save test  :request={}", testDto);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      testService.saveAndUpdateTest(testDto);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Test saved successfully");
    }catch (Exception e){
      LOGGER.error("Error  saveTest  : error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to save test");
    }
    LOGGER.info("Save test  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @GetMapping("/get-test")
  public ResponseEntity<LabAppointmentResponse> getTest(
      @RequestParam(value = "testType",required = false) String testType,
      @RequestParam(value = "testId",required = false) String testId) {
    LOGGER.info("Get test request  :testType={} | testId={}",testType,testId);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      TestDto testDto = testService.getTest(testType,testId);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("test detail retrieved successfully");
      labAppointmentResponse.setData(testDto);
    }catch (Exception e){
      LOGGER.info("Error  getTest: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to retrieved test detail");
    }
    LOGGER.info("Get test response  : response={}",labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @GetMapping("/get-all-test")
  public ResponseEntity<LabAppointmentResponse> getAlltest(){
    LOGGER.info("Get all test request");
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      List<TestDto> testDtoList = testService.getAllTest();
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("tests details retrieved successfully");
      labAppointmentResponse.setData(testDtoList);
    }catch (Exception e){
      LOGGER.error("Error  getAllTest: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to retrieved tests details");
    }
    LOGGER.info("Get all test response  :  response={}",labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @PutMapping("/update-test")
  public ResponseEntity<LabAppointmentResponse> updateTest(@RequestBody TestDto testDto){
    LOGGER.info("Update test  :request={}", testDto);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      testService.saveAndUpdateTest(testDto);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("test update successfully");
    }catch (Exception e){
      LOGGER.error("Error  updateTest: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to update test");
    }
    LOGGER.info("Update test response  : response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @DeleteMapping("/delete-test")
  public ResponseEntity<LabAppointmentResponse> deleteTest(@RequestParam(value = "testId",required = false) Long testId){
    LOGGER.info("Delete test  :testId={}", testId);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      testService.deleteTest(testId);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("test delete successfully");
    }catch (Exception e){
      LOGGER.error("Error  deleteTest  : error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to delete test");
    }
    LOGGER.info("Delete test  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
}
