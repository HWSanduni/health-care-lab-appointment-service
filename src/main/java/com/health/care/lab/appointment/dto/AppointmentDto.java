package com.health.care.lab.appointment.dto;

import com.health.care.lab.appointment.enums.AppointmentType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AppointmentDto {
    private Long id;
    private AppointmentType appointmentType;
    private String time;
    private LocalDateTime date;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private PatientDto patient;
    private DoctorDto doctor;
    private TechnicianDto technician;

}
