package com.health.care.lab.appointment.entity;

import com.health.care.lab.appointment.enums.StatusType;
import com.health.care.lab.appointment.enums.TestType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Data;

@Data
@Entity
@Table(name = "technician")
public class Technician {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "technician_Id")
  private String technicianId;

  @Column(name = "name")
  private String name;

  @Column(name = "tel_number")
  private String telNumber;

  @Column(name = "email")
  private String email;

  @Column(name = "test_type")
  @Enumerated(EnumType.STRING)
  private TestType testType;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private StatusType status;

  @OneToMany(mappedBy="technician")
  private Set<Appointment> appointments;

  @OneToMany(mappedBy="technician")
  private Set<Report> reports;

  @OneToMany(mappedBy="technician")
  private Set<Test> tests;

  @OneToMany(mappedBy="technician")
  private Set<TestResult> testResults;
}
