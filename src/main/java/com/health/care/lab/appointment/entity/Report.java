package com.health.care.lab.appointment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "report")
public class Report {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "content")
  private String content;

  @ManyToOne
  @JoinColumn(name="patient_Id", nullable=false)
  private Patient patient;

  @ManyToOne
  @JoinColumn(name="doctor_Id", nullable=false)
  private Doctor doctor;

  @ManyToOne
  @JoinColumn(name="technician_Id", nullable=false)
  private Technician technician;

}
