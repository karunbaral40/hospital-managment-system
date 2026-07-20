package com.hospital.Hospital.Management.System.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PatientRequestDTO {

    @NotBlank(message = "Name must not be empty")
    @Size(min = 2, max = 100, message = "Name must be 2 to 100 characters")
    private String name;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    private String email;

    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 120, message = "Age cannot exceed 120")
    private Integer age;

    @NotBlank(message = "Disease cannot be empty")
    private String disease;

    @NotNull(message = "Admit date cannot be null")
    private LocalDate admitDate;

    private Boolean serious = false;

    public PatientRequestDTO() {}

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
}