package com.hospital.Hospital.Management.System.Services;

import com.hospital.Hospital.Management.System.Entity.DoctorEntity;
import com.hospital.Hospital.Management.System.Entity.PatientEntity;
import com.hospital.Hospital.Management.System.Exception.BadRequestException;
import com.hospital.Hospital.Management.System.Exception.DuplicateResourceException;
import com.hospital.Hospital.Management.System.Exception.ResourceNotFoundException;
import com.hospital.Hospital.Management.System.Reposetry.DoctorRepo;
import com.hospital.Hospital.Management.System.Reposetry.PatientRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientServices {

    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;

    public PatientServices(PatientRepo patientRepo, DoctorRepo doctorRepo) {
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    public List<PatientEntity> findAll() {
        return patientRepo.findAll();
    }

    public PatientEntity findById(int id) {
        return patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient with id " + id + " not found"));
    }

    public PatientEntity findByName(String name) {
        return patientRepo.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient with name " + name + " not found"));
    }


    public List<PatientEntity> findBySerious(Boolean serious) {
        return patientRepo.findBySerious(serious);
    }

    public PatientEntity savePatient(PatientEntity patientEntity, Integer doctorId) {


        if (patientRepo.existsByEmail(patientEntity.getEmail())) {
            throw new DuplicateResourceException(
                    "Patient with email " + patientEntity.getEmail() + " already exists");
        }


        DoctorEntity doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Doctor with id " + doctorId + " not found"));

        if (!doctor.getAvailable()) {
            throw new BadRequestException(
                    "Doctor with id " + doctorId + " is not available");
        }


        patientEntity.setDoctor(doctor);

        return patientRepo.save(patientEntity);
    }

    public PatientEntity updateByPatientId(int id, PatientEntity patientEntity) {
        PatientEntity patient = patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient with id " + id + " not found"));
        patient.setName(patientEntity.getName());
        patient.setEmail(patientEntity.getEmail());
        patient.setAge(patientEntity.getAge());
        patient.setDisease(patientEntity.getDisease());
        patient.setAdmitDate(patientEntity.getAdmitDate());
        patient.setSerious(patientEntity.getSerious());
        return patientRepo.save(patient);
    }

    public String deletePatientById(Integer id) {
        patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient with id " + id + " not found"));
        patientRepo.deleteById(id);
        return "Patient with id " + id + " deleted successfully";
    }

    public long countAll() {
        return patientRepo.count();
    }

    public long countSerious() {
        return patientRepo.findBySerious(true).size();
    }

    public List<PatientEntity> findByDoctorId(int id) {
        return patientRepo.findByDoctorId(id);
    }
}