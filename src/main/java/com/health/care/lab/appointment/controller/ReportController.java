package com.health.care.lab.appointment.controller;

import com.health.care.lab.appointment.dto.ReportDto;
import com.health.care.lab.appointment.dto.response.LabAppointmentResponse;
import com.health.care.lab.appointment.service.ReportService;
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
@RequestMapping("v1/report")
@CrossOrigin
public class ReportController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

  @Autowired
  private ReportService reportService;

  @PostMapping("/save-report")
  public ResponseEntity<LabAppointmentResponse> saveReport(@RequestBody ReportDto reportDto){

    LOGGER.info("Save report  :request={}", reportDto);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      reportService.saveAndUpdateReport(reportDto);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Report saved successfully");
    }catch (Exception e){
      LOGGER.error("Error  saveReport  : error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to save report");
    }
    LOGGER.info("Save report  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @GetMapping("/get-report")
  public ResponseEntity<LabAppointmentResponse> getReport(
      @RequestParam(value = "reportType",required = false) String reportType,
      @RequestParam(value = "reportId",required = false) String reportId) {
    LOGGER.info("Get report request  :reportType={} | reportId={}",reportType,reportId);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
//      reportDto reportDto = reportService.getreport(reportType,reportId);
//      labAppointmentResponse.setStatus(HttpStatus.OK);
//      labAppointmentResponse.setMessage("report detail retrieved successfully");
//      labAppointmentResponse.setData(reportDto);
    }catch (Exception e){
      LOGGER.info("Error  getReport: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to retrieved report detail");
    }
    LOGGER.info("Get report response  : response={}",labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @GetMapping("/get-all-report")
  public ResponseEntity<LabAppointmentResponse> getAllReport(){
    LOGGER.info("Get all report request");
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      List<ReportDto> reportDtoList = reportService.getAllReport();
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("Reports details retrieved successfully");
      labAppointmentResponse.setData(reportDtoList);
    }catch (Exception e){
      LOGGER.error("Error  getAllReport: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to retrieved reports details");
    }
    LOGGER.info("Get all report response  :  response={}",labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @PutMapping("/update-report")
  public ResponseEntity<LabAppointmentResponse> updateReport(@RequestBody ReportDto reportDto){
    LOGGER.info("Update report  :request={}", reportDto);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      reportService.saveAndUpdateReport(reportDto);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("report update successfully");
    }catch (Exception e){
      LOGGER.error("Error  updateReport: error={}",e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to update report");
    }
    LOGGER.info("Update report response  : response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
  @DeleteMapping("/delete-report")
  public ResponseEntity<LabAppointmentResponse> deleteReport(@RequestParam(value = "reportId",required = false) Long reportId){
    LOGGER.info("Delete report  :reportId={}", reportId);
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    try {
      reportService.deleteReport(reportId);
      labAppointmentResponse.setStatus(HttpStatus.OK);
      labAppointmentResponse.setMessage("report delete successfully");
    }catch (Exception e){
      LOGGER.error("Error  deleteReport  : error={}", e.getMessage());
      labAppointmentResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      labAppointmentResponse.setMessage("Failed to delete report");
    }
    LOGGER.info("Delete report  response  :response={}", labAppointmentResponse);
    return ResponseEntity.ok(labAppointmentResponse);
  }
}
