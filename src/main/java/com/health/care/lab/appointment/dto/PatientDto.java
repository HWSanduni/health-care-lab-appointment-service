package com.health.care.lab.appointment.dto;


import java.util.List;
import lombok.Data;

@Data
  public class PatientDto {
    private Long id;
    private String patientId;
    private String name;
    private String telNumber;
    private String address;
    private String email;
    private String gender;
    private String age;
  private List<AppointmentDto> appointments;
  private List<ReportDto> reports;
  private List<TestDto> tests;
  private List<TestResultDto> testResults;

}
