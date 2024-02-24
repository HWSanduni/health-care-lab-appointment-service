package com.health.care.lab.appointment.entity;

import com.health.care.lab.appointment.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "password")
  private String password;

  @Column(name = "user_Name")
  private String userName;

  @Column(name = "user_type")
  @Enumerated(EnumType.STRING)
  private UserType userType;


}
