package com.hospital.Hospital.Management.System.dto;

import jakarta.validation.constraints.*;

public class DoctorRequestDTO {

    @NotBlank(message = "Doctor name cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be 2 to 100 characters")
    private String name;

    @NotBlank(message = "Specialization cannot be empty")
    private String specialization;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please provide valid email")
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    private Boolean available = true;

    public DoctorRequestDTO() {}

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}