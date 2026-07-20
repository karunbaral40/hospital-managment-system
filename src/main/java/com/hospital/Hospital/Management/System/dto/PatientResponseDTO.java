package com.hospital.Hospital.Management.System.dto;

import java.time.LocalDate;

public class PatientResponseDTO {

    private Integer id;
    private String name;
    private String email;
    private Integer age;
    private String disease;
    private LocalDate admitDate;
    private Boolean serious;
    private String doctorName;

    public PatientResponseDTO() {}

    public PatientResponseDTO(Integer id, String name, String email, Integer age, String disease, LocalDate admitDate, Boolean serious, String doctorName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.disease = disease;
        this.admitDate = admitDate;
        this.serious = serious;
        this.doctorName = doctorName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public LocalDate getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(LocalDate admitDate) {
        this.admitDate = admitDate;
    }

    public Boolean getSerious() {
        return serious;
    }

    public void setSerious(Boolean serious) {
        this.serious = serious;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}