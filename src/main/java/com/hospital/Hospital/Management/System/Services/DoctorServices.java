package com.hospital.Hospital.Management.System.Services;

import com.hospital.Hospital.Management.System.Entity.DoctorEntity;
import com.hospital.Hospital.Management.System.Exception.DuplicateResourceException;
import com.hospital.Hospital.Management.System.Exception.ResourceNotFoundException;
import com.hospital.Hospital.Management.System.Reposetry.DoctorRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorServices {

    private final DoctorRepo doctorRepo;           // private final — correct

    public DoctorServices(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;              // constructor injection only
    }

    public List<DoctorEntity> findAllDoctor() {
        return doctorRepo.findAll();
    }

    public DoctorEntity getDoctorById(Integer id) {
        return doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Doctor with id " + id + " not found"));
    }

    public List<DoctorEntity> findByName(String name) {
        List<DoctorEntity> doctors = doctorRepo.findByName(name);
        if (doctors.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Doctor with name " + name + " not found");
        }
        return doctors;
    }

    public List<DoctorEntity> findBySpecialization(String specialization) {
        return doctorRepo.findBySpecialization(specialization);
    }

    public List<DoctorEntity> getAvailableDoctors(Boolean available) {
        return doctorRepo.findByAvailable(available);
    }

    public DoctorEntity saveDoctor(DoctorEntity doctorEntity) {
        // Business rule: check duplicate email
        if (doctorRepo.existsByEmail(doctorEntity.getEmail())) {
            throw new DuplicateResourceException(
                    "Doctor with email " + doctorEntity.getEmail() + " already exists");
        }
        return doctorRepo.save(doctorEntity);
    }

    public DoctorEntity updateDoctor(int id, DoctorEntity doctorEntity) {
        DoctorEntity exist = doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Doctor with id " + id + " not found"));
        // Never set id from body — only from URL path
        exist.setName(doctorEntity.getName());
        exist.setEmail(doctorEntity.getEmail());
        exist.setPhone(doctorEntity.getPhone());
        exist.setSpecialization(doctorEntity.getSpecialization());
        exist.setAvailable(doctorEntity.getAvailable());
        return doctorRepo.save(exist);
    }

    public String deleteDoctorById(Integer id) {
        doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Doctor with id " + id + " not found"));
        doctorRepo.deleteById(id);
        return "Doctor with id " + id + " deleted successfully";
    }

    public String deleteDoctorByName(String name) {
        List<DoctorEntity> doctors = doctorRepo.findByName(name);
        if (doctors.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Doctor with name " + name + " not found");
        }
        doctorRepo.deleteByName(name);
        return "Doctor with name " + name + " deleted successfully";
    }

    public long countAll() {
        return doctorRepo.count();
    }

    public long countAvailable() {
        return doctorRepo.findByAvailable(true).size();
    }
}