package com.health.care.lab.appointment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.health.care.lab.appointment.enums.AppointmentType;
import com.health.care.lab.appointment.enums.StatusType;
import com.health.care.lab.appointment.enums.TestType;
import java.time.LocalDateTime;
import lombok.Data;


@Data
public class AppointmentDto {
    private Long id;
    private String testType;
    private String time;
    private LocalDateTime date;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private StatusType status;
    private PatientDto patient;
    private DoctorDto doctor;
    private TechnicianDto technician;

}
