package com.health.care.lab.appointment.dto;

import com.health.care.lab.appointment.enums.StatusType;
import java.util.List;
import lombok.Data;

@Data
  public class DoctorDto {
    private Long id;
    private String doctorID;
    private String name;
    private String telNumber;
    private String nic;
    private String email;
  private StatusType status;
   private List<AppointmentDto> appointments;
  private List<ReportDto> reports;
  private List<TestDto> tests;
  private List<TestResultDto> testResults;


}
