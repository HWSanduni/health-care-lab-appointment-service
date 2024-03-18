package com.health.care.lab.appointment.controller;

import com.health.care.lab.appointment.dto.LoginDto;
import com.health.care.lab.appointment.dto.RegisterRequestDto;
import com.health.care.lab.appointment.dto.UserDto;
import com.health.care.lab.appointment.dto.response.AuthenticationResponse;
import com.health.care.lab.appointment.dto.response.LabAppointmentResponse;
import com.health.care.lab.appointment.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/authentication")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<LabAppointmentResponse> signIn (@RequestBody LoginDto loginDto){
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();

    AuthenticationResponse authenticate = authenticationService.authenticate(loginDto);
    labAppointmentResponse.setData(authenticate);
    labAppointmentResponse.setStatus(HttpStatus.OK);
    labAppointmentResponse.setMessage("Appointment saved successfully");
    return ResponseEntity.ok(labAppointmentResponse);
  }

  @PostMapping("/registration")
  public ResponseEntity<LabAppointmentResponse> signUp (@RequestBody RegisterRequestDto registerRequestDto){
    LabAppointmentResponse labAppointmentResponse = new LabAppointmentResponse();
    AuthenticationResponse authenticate = authenticationService.singUp(registerRequestDto);
    labAppointmentResponse.setData(authenticate);
    labAppointmentResponse.setStatus(HttpStatus.OK);
    labAppointmentResponse.setMessage("Registered successfully");
    return ResponseEntity.ok(labAppointmentResponse);
  }

}
