package com.health.care.lab.appointment.dto;

import com.health.care.lab.appointment.enums.TestType;
import com.health.care.lab.appointment.enums.UserType;
import lombok.Data;

@Data
public class RegisterRequestDto {
  private Long id;
  private String password;
  private String userName;
  private UserType userType;
  private String userId;
  private String firstName;
  private String lastName;
  private String telNumber;
  private String address;
  private String email;
  private String gender;
  private String age;
  private String nic;
  private TestType testType;
  private String createdBy;
}
