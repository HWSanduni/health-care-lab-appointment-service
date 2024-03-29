package com.health.care.lab.appointment.service.impl;

import static com.health.care.lab.appointment.util.CommonUtil.generateRandomNumber;

import com.health.care.lab.appointment.dto.LoginDto;
import com.health.care.lab.appointment.dto.PatientDto;
import com.health.care.lab.appointment.dto.RegisterRequestDto;
import com.health.care.lab.appointment.dto.UserDto;
import com.health.care.lab.appointment.dto.response.AuthenticationResponse;
import com.health.care.lab.appointment.enums.UserType;
import com.health.care.lab.appointment.repository.UserRepository;
import com.health.care.lab.appointment.service.AuthenticationService;
import com.health.care.lab.appointment.service.DoctorService;
import com.health.care.lab.appointment.service.PatientService;
import com.health.care.lab.appointment.service.TechnicianService;
import com.health.care.lab.appointment.service.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  @Autowired
  private JWTServiceImpl JWTServiceImpl;

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private PatientService patientService;

  @Autowired
  private DoctorService doctorService;

  @Autowired
  private TechnicianService technicianService;
  
  @Autowired
  AutUserDetailsImpl autUserDetails;

  @Autowired
  private UserRepository userRepository;

  @Override
  public AuthenticationResponse authenticate(LoginDto loginDto) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginDto.getUserName(),
            loginDto.getPassword()
        )
    );


    UserDto userDto = userService.findByUser(loginDto.getUserName());
    String jwtToken = JWTServiceImpl.generateToken(new User(userDto.getUserName(),userDto.getPassword(),new ArrayList<>()));
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .tokenType("Bearer")
        .userid(userDto.getUserId())
        .userType(userDto.getUserType().name())
        .status("200")
        .build();
  }

  @Override
  public AuthenticationResponse singUp(RegisterRequestDto registerRequestDto) {

    String userId = null;

    if (registerRequestDto.getUserType().toString().equals("DOCTOR")){
      userId=generateRandomNumber("DOC");
      registerRequestDto.setUserId(userId);
      doctorService.saveAndUpdateDoctor(registerRequestDto);
    } else if (registerRequestDto.getUserType().toString().equals("PATIENT")) {
      userId=generateRandomNumber("PAT");
      registerRequestDto.setUserId(userId);
      patientService.saveAndUpdatePatient(registerRequestDto);
    } else if (registerRequestDto.getUserType().toString().equals("TECHNICIAN")) {
      userId=generateRandomNumber("TEC");
      registerRequestDto.setUserId(userId);
      technicianService.saveAndUpdateTechnician(registerRequestDto);
    }
    UserDto userDto = new UserDto();
    userDto.setUserName(registerRequestDto.getUserName());
    userDto.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
    userDto.setUserType(registerRequestDto.getUserType());
    userDto.setUserId(userId);

    userService.saveUser(userDto);

    UserDetails userDetails = new User(userDto.getUserName(),userDto.getPassword(),new ArrayList<>());
    String jwtToken = JWTServiceImpl.generateToken(userDetails);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .tokenType("Bearer")
        .userid(userDto.getUserId())
        .userType(userDto.getUserType().name())
        .status("200")
        .build();
  }
}
