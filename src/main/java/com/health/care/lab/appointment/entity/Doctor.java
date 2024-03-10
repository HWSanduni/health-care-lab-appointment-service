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
@Table(name = "doctor")
  public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "doctor_Id")
    private String doctorID;

    @Column(name = "name")
    private String name;

    @Column(name = "tel_number")
    private String telNumber;

     @Column(name = "nic")
     private String nic;

    @Column(name = "email")
    private String email;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private StatusType status;

   @OneToMany(mappedBy="doctor")
   private Set<Appointment> appointments;

  @OneToMany(mappedBy="doctor")
  private Set<Report> reports;

  @OneToMany(mappedBy="doctor")
  private Set<Test> tests;

  @OneToMany(mappedBy="doctor")
  private Set<TestResult> testResults;


}
