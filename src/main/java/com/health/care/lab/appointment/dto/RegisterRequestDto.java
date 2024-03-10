package com.health.care.lab.appointment.dto;

import com.health.care.lab.appointment.enums.UserType;
import lombok.Data;

@Data
public class RegisterRequestDto {
  private Long id;
  private String password;
  private String userName;
  private UserType userType;
  private String userId;
  private String name;
  private String telNumber;
  private String address;
  private String email;
  private String gender;
  private String age;
  private String nic;
}
