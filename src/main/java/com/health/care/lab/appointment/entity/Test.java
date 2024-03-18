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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "test")
public class Test {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "test_id")
  private String testId;

  @Column(name = "test_type")
  @Enumerated(EnumType.STRING)
  private TestType testType;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private StatusType status;

  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @Column(name = "updated_date")
  private LocalDateTime updatedDate;

  @ManyToOne
  @JoinColumn(name="patient_Id", nullable=false)
  private Patient patient;

  @ManyToOne
  @JoinColumn(name="doctor_Id", nullable=false)
  private Doctor doctor;

  @ManyToOne
  @JoinColumn(name="technician_Id",nullable=false)
  private Technician technician;


  @OneToOne(mappedBy="test")
  private TestResult testResult;
}
