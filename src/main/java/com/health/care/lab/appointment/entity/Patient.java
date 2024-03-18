package com.health.care.lab.appointment.entity;

import com.health.care.lab.appointment.enums.StatusType;
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
@Table(name = "patient")
  public class Patient {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
    private Long id;

     @Column(name = "patient_Id")
     private String patientId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

    @Column(name = "nic")
    private String nic;

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

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private StatusType status;

   @OneToMany(mappedBy="patient")
   private Set<DoctorAppointment> doctorAppointments;

  @OneToMany(mappedBy="patient")
  private Set<Report> reports;

  @OneToMany(mappedBy="patient")
  private Set<Test> tests;

  @OneToMany(mappedBy="patient")
  private Set<TestResult> testResults;

}
