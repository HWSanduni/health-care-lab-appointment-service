package com.health.care.lab.appointment.dto;

import com.health.care.lab.appointment.enums.UserType;
import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class UserDto{
  private Long id;
  private String password;
  private String userName;
  private UserType userType;
  private String userId;
}
