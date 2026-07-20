package com.hospital.Hospital.Management.System.Controller;

import com.hospital.Hospital.Management.System.Services.DoctorServices;
import com.hospital.Hospital.Management.System.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorServices doctorServices;
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    public DoctorController(DoctorServices doctorServices,
                            DoctorMapper doctorMapper,
                            PatientMapper patientMapper) {
        this.doctorServices = doctorServices;
        this.doctorMapper = doctorMapper;
        this.patientMapper = patientMapper;
    }

    // GET all — returns list of DoctorResponseDTO (no patients)
    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> findAll() {
        return ResponseEntity.ok(
                doctorMapper.toResponseDTOList(
                        doctorServices.findAllDoctor()));
    }

    // GET by id — returns DoctorWithPatientsDTO (with patients)
    @GetMapping("/{id}")
    public ResponseEntity<DoctorWithPatientsDTO> getById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(
                doctorMapper.toDoctorWithPatientsDTO(
                        doctorServices.getDoctorById(id), patientMapper));
    }

    // GET by name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<DoctorResponseDTO>> getByName(
            @PathVariable String name) {
        return ResponseEntity.ok(
                doctorMapper.toResponseDTOList(
                        doctorServices.findByName(name)));
    }

    // GET by specialization
    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<DoctorResponseDTO>> getBySpecialization(
            @PathVariable String specialization) {
        return ResponseEntity.ok(
                doctorMapper.toResponseDTOList(
                        doctorServices.findBySpecialization(specialization)));
    }

    // GET available
    @GetMapping("/available/{available}")
    public ResponseEntity<List<DoctorResponseDTO>> getAvailable(
            @PathVariable Boolean available) {
        return ResponseEntity.ok(
                doctorMapper.toResponseDTOList(
                        doctorServices.getAvailableDoctors(available)));
    }

    // POST — receives DoctorRequestDTO, returns DoctorResponseDTO
    @PostMapping
    public ResponseEntity<DoctorResponseDTO> save(
            @Valid @RequestBody DoctorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(doctorMapper.toResponseDTO(
                        doctorServices.saveDoctor(
                                doctorMapper.toEntity(dto))));
    }

    // PUT — receives DoctorRequestDTO, returns DoctorResponseDTO
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody DoctorRequestDTO dto) {
        return ResponseEntity.ok(
                doctorMapper.toResponseDTO(
                        doctorServices.updateDoctor(id,
                                doctorMapper.toEntity(dto))));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(
                doctorServices.deleteDoctorById(id));
    }
}