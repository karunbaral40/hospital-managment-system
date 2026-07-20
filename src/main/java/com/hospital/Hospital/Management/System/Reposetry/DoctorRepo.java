package com.hospital.Hospital.Management.System.Reposetry;

import com.hospital.Hospital.Management.System.Entity.DoctorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<DoctorEntity, Integer> {

    List<DoctorEntity> findBySpecialization(String specialization);

    List<DoctorEntity> findByName(String name);         // was findByDoctorName

    List<DoctorEntity> findByAvailable(Boolean available);

    boolean existsByEmail(String email);                // needed for duplicate check

    @Modifying
    @Transactional
    @Query("DELETE FROM DoctorEntity d WHERE d.name = :name")
    void deleteByName(@Param("name") String name);      // was deleteByDoctorName
}