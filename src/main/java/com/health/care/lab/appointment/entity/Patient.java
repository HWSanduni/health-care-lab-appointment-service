package com.health.care.lab.appointment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "patient")
  public class Patient {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
    private Long id;

     @Column(name = "patient_Id")
    private String patientId;

     @Column(name = "name")
    private String name;

    @Column(name = "tel_number")
    private String telNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String age;

   @OneToMany(mappedBy="patient")
   private Set<Appointment> appointments;

  @OneToMany(mappedBy="patient")
  private Set<Report> reports;

  @OneToMany(mappedBy="patient")
  private Set<Test> tests;

  @OneToMany(mappedBy="patient")
  private Set<TestResult> testResults;

}
