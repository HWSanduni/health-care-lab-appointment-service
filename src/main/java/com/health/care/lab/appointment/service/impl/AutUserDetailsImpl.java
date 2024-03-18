package com.health.care.lab.appointment.service.impl;

import com.health.care.lab.appointment.dto.UserDto;
import com.health.care.lab.appointment.repository.UserRepository;
import com.health.care.lab.appointment.service.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutUserDetailsImpl implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDto userDto = userService.findByUser(username);
    UserDetails userDetails = new User(userDto.getUserName(),userDto.getPassword(),new ArrayList<>());

    return userDetails;

  }
}
