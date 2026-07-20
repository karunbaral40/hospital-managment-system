package com.hospital.Hospital.Management.System.Reposetry;

import com.hospital.Hospital.Management.System.Entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<PatientEntity, Integer> {

    List<PatientEntity> findBySerious(Boolean serious);

    List<PatientEntity> findByDoctorId(Integer doctorId);


    long countByDoctorId(Integer doctorId);

    Optional<PatientEntity> findByName(String name);

    boolean existsByEmail(String email);


}