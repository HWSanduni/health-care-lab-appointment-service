package com.health.care.lab.appointment.dto;

import com.health.care.lab.appointment.enums.StatusType;
import com.health.care.lab.appointment.enums.TestType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TestDto {
  private Long id;
  private String testId;
  private TestType testType;
  private StatusType status;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private PatientDto patient;
  private DoctorDto doctor;
  private TechnicianDto technician;
  private TestResultDto testResult;
}
