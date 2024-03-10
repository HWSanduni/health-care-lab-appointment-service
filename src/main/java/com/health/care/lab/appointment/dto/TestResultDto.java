package com.health.care.lab.appointment.dto;

import com.health.care.lab.appointment.enums.StatusType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TestResultDto {
  private Long id;
  private String testResultContent;
  private String testResultId;
  private StatusType status;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private PatientDto patient;
  private DoctorDto doctor;
  private TechnicianDto technician;
  private TestDto test;


}
