package com.hospital.Hospital.Management.System.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "doctors")
public class DoctorEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @NotBlank(message = "Doctor name cannot be empty")
   @Size(min = 2, max = 100, message = "Name must be 2 to 100 characters")
   private String name;

   @NotBlank(message = "Specialization must not be empty")
   private String specialization;

   @NotBlank(message = "Email cannot be empty")
   @Email(message = "Please provide a valid email")
   @Column(unique = true)
   private String email;

   @NotBlank(message = "Please provide phone number")
   @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be exactly 10 digits")
   @Column(unique = true)
   private String phone;

   @Column(nullable = false)
   private Boolean available = true;

   @OneToMany(mappedBy="doctor",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
   @JsonManagedReference
   private List<PatientEntity> patients;

   public DoctorEntity() {}

   public DoctorEntity(String name, String specialization, String email, String phone, Boolean available) {
       this.name = name;
       this.specialization = specialization;
       this.email = email;
       this.phone = phone;
       this.available = available;
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

   public List<PatientEntity> getPatients() {
       return patients;
   }

   public void setPatients(List<PatientEntity> patients) {
       this.patients = patients;
   }
}