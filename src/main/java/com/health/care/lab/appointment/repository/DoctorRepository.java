package com.health.care.lab.appointment.repository;

import com.health.care.lab.appointment.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

  @Query(value = "SELECT * FROM Doctor d " +
      "WHERE (d.name IS NULL OR d.name = :paramName OR d.name IS NULL) " +
      "AND (d.nic IS NULL OR d.nic = :paramNic OR d.nic IS NULL) " +
      "AND (d.doctor_id IS NULL OR d.doctor_id = :paramDoctorId OR d.doctor_id IS NULL)",nativeQuery = true)
  Doctor findDoctorsByOptionalParameters(
      @Param("paramName") String paramName,
      @Param("paramNic") String paramNic,
      @Param("paramDoctorId") String paramDoctorId
  );

}
