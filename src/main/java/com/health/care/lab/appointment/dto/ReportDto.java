package com.health.care.lab.appointment.dto;


import lombok.Data;

@Data
public class ReportDto {
  private Long id;
  private String content;
  private PatientDto patient;
  private DoctorDto doctor;
  private TechnicianDto technician;

}
