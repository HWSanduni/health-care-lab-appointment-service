package com.health.care.lab.appointment.service;

import com.health.care.lab.appointment.dto.UserDto;

public interface UserService {

  void saveUser(UserDto userDto);

  UserDto findByUser(String userName);
}
