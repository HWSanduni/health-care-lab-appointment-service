package com.health.care.lab.appointment.dto;

import com.health.care.lab.appointment.enums.UserType;
import lombok.Data;

@Data
public class UserDto {
  private Long id;
  private String password;
  private String userName;
  private UserType userType;

}
