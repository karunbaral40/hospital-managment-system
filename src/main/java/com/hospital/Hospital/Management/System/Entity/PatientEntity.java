package com.hospital.Hospital.Management.System.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name must not be empty")
    @Size(min = 2, max = 100, message = "Name must be 2 to 100 characters")
    private String name;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email must be in valid format")
    @Column(unique = true)
    private String email;

    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 120, message = "Age cannot exceed 120")
    private Integer age;

    @NotBlank(message = "Disease cannot be empty")
    private String disease;

    @NotNull(message = "Admit date cannot be null")
    private LocalDate admitDate;

    private Boolean serious;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private DoctorEntity doctor;

    public PatientEntity() {}

    public PatientEntity(String name, String email, Integer age, String disease, LocalDate admitDate, Boolean serious) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.disease = disease;
        this.admitDate = admitDate;
        this.serious = serious;
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

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }
}