package com.hospital.Hospital.Management.System.dto;

import java.util.List;

public class DoctorWithPatientsDTO {

    private Integer id;
    private String name;
    private String specialization;
    private String email;
    private Boolean available;
    private List<PatientResponseDTO> patients;

    public DoctorWithPatientsDTO() {}

    public DoctorWithPatientsDTO(Integer id, String name, String specialization, String email, Boolean available, List<PatientResponseDTO> patients) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.available = available;
        this.patients = patients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<PatientResponseDTO> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientResponseDTO> patients) {
        this.patients = patients;
    }
}