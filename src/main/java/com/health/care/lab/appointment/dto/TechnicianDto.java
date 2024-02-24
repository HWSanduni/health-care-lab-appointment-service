package com.health.care.lab.appointment.dto;


import com.health.care.lab.appointment.enums.TestType;
import java.util.List;
import lombok.Data;

@Data
public class TechnicianDto {
  private Long id;
  private String technicianId;
  private String name;
  private String telNumber;
  private String email;
  private TestType testType;

  private List<AppointmentDto> appointments;
  private List<ReportDto> reports;
  private List<TestDto> tests;
  private List<TestResultDto> testResults;
}
