package com.hospital.Hospital.Management.System.Controller;

import com.hospital.Hospital.Management.System.Services.PatientServices;
import com.hospital.Hospital.Management.System.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientServices patientServices;
    private final PatientMapper patientMapper;

    public PatientController(PatientServices patientServices,
                             PatientMapper patientMapper) {
        this.patientServices = patientServices;
        this.patientMapper = patientMapper;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAll() {
        return ResponseEntity.ok(
                patientMapper.toResponseDTOList(
                        patientServices.findAll()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PatientResponseDTO> getById(
            @PathVariable int id) {
        return ResponseEntity.ok(
                patientMapper.toResponseDTO(
                        patientServices.findById(id)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PatientResponseDTO> getByName(
            @PathVariable String name) {
        return ResponseEntity.ok(
                patientMapper.toResponseDTO(
                        patientServices.findByName(name)));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<PatientResponseDTO>> getByDoctor(
            @PathVariable int doctorId) {
        return ResponseEntity.ok(
                patientMapper.toResponseDTOList(
                        patientServices.findByDoctorId(doctorId)));
    }

    @GetMapping("/serious/{serious}")
    public ResponseEntity<List<PatientResponseDTO>> getBySerious(
            @PathVariable Boolean serious) {
        return ResponseEntity.ok(
                patientMapper.toResponseDTOList(
                        patientServices.findBySerious(serious)));
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> save(
            @Valid @RequestBody PatientRequestDTO dto,
            @RequestParam Integer doctorId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(patientMapper.toResponseDTO(
                        patientServices.savePatient(
                                patientMapper.toEntity(dto), doctorId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(
            @PathVariable int id,
            @Valid @RequestBody PatientRequestDTO dto) {
        return ResponseEntity.ok(
                patientMapper.toResponseDTO(
                        patientServices.updateByPatientId(id,
                                patientMapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(
                patientServices.deletePatientById(id));
    }
}