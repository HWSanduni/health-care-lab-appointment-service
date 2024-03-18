package com.health.care.lab.appointment.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class LabAppointmentResponse {

  private HttpStatus status;
  private String message;
  private Object data;

}
