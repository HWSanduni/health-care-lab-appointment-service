package com.health.care.lab.appointment.service;

import com.health.care.lab.appointment.dto.LoginDto;
import com.health.care.lab.appointment.dto.RegisterRequestDto;
import com.health.care.lab.appointment.dto.UserDto;
import com.health.care.lab.appointment.dto.response.AuthenticationResponse;

public interface AuthenticationService {

  AuthenticationResponse authenticate(LoginDto loginDto);
  AuthenticationResponse singUp(RegisterRequestDto registerRequestDto);
}
