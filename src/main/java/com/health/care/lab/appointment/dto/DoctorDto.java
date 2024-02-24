package com.health.care.lab.appointment.dto;

import java.util.List;
import lombok.Data;

@Data
  public class DoctorDto {
    private Long id;
    private String doctorID;
    private String name;
    private String telNumber;
    private String email;
   private List<AppointmentDto> appointments;
  private List<ReportDto> reports;
  private List<TestDto> tests;
  private List<TestResultDto> testResults;


}
