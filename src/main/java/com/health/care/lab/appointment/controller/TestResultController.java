package com.health.care.lab.appointment.controller;

import com.health.care.lab.appointment.dto.TestResultDto;
import com.health.care.lab.appointment.dto.response.LabAppointmentResponse;
import com.health.care.lab.appointment.service.TestResultService;
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
@RequestMapping("/v1/testResult")
@CrossOrigin
public class TestResultController {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestResultController.class);

  @Autowired
  private TestResultService testResultService;

  @PostMapping("/save-testResult")
  public ResponseEntity<LabAppointmentResponse> saveTestResult(@RequestBody TestResultDto testResultDto){

    LOGGER.info("Save testResult  :request={}", testResultDto);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      testResultService.saveAndUpdateTestResult(testResultDto);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("testResult saved successfully");
    }catch (Exception e){
      LOGGER.error("Error  savetestResult  : error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to save testResult");
    }
    LOGGER.info("Save testResult  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @GetMapping("/get-testResult")
  public ResponseEntity<LabAppointmentResponse> getTestResult(
      @RequestParam(value = "testResultType",required = false) String testResultType,
      @RequestParam(value = "testResultId",required = false) String testResultId) {
    LOGGER.info("Get testResult request  :testResultType={} | testResultId={}",testResultType,testResultId);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      TestResultDto testResultDto = testResultService.getTestResult(testResultType,testResultId);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("testResult detail retrieved successfully");
      labAppointmentResponse.setData(testResultDto);
    }catch (Exception e){
      LOGGER.info("Error  gettestResult: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to retrieved testResult detail");
    }
    LOGGER.info("Get testResult response  : response={}",labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @GetMapping("/get-all-testResult")
  public ResponseEntity<LabAppointmentResponse> getAllTestResult(){
    LOGGER.info("Get all testResult request");
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      List<TestResultDto> testResultDtoList = testResultService.getAllTestResult();
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("testResults details retrieved successfully");
      labAppointmentResponse.setData(testResultDtoList);
    }catch (Exception e){
      LOGGER.error("Error  getAllTestResult: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to retrieved testResults details");
    }
    LOGGER.info("Get all testResult response  :  response={}",labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @PutMapping("/update-testResult")
  public ResponseEntity<LabAppointmentResponse> updateTestResult(@RequestBody TestResultDto testResultDto){
    LOGGER.info("Update testResult  :request={}", testResultDto);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      testResultService.saveAndUpdateTestResult(testResultDto);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("testResult update successfully");
    }catch (Exception e){
      LOGGER.error("Error  updateTestResult: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to update testResult");
    }
    LOGGER.info("Update testResult response  : response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @DeleteMapping("/delete-testResult")
  public ResponseEntity<LabAppointmentResponse> deleteTestResult(@RequestParam(value = "testResultId",required = false) Long testResultId){
    LOGGER.info("Delete testResult  :testResultId={}", testResultId);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      testResultService.deleteTestResult(testResultId);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("testResult delete successfully");
    }catch (Exception e){
      LOGGER.error("Error  deleteTestResult  : error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to delete testResult");
    }
    LOGGER.info("Delete testResult  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
}
