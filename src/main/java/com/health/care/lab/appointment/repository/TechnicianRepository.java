package com.health.care.lab.appointment.repository;

import com.health.care.lab.appointment.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician,Long> {

  @Query(value = "select * from technician where technician_id=?",nativeQuery = true)
  Technician findByTechnicianId(String techId);

}
