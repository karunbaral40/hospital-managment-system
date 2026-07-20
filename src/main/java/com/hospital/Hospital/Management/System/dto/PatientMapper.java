package com.hospital.Hospital.Management.System.dto;

import com.hospital.Hospital.Management.System.Entity.PatientEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {

    // Entity → ResponseDTO
    public PatientResponseDTO toResponseDTO(PatientEntity patient) {
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setEmail(patient.getEmail());
        dto.setAge(patient.getAge());
        dto.setDisease(patient.getDisease());
        dto.setAdmitDate(patient.getAdmitDate());
        dto.setSerious(patient.getSerious());

        // Get doctor name from relationship
        if (patient.getDoctor() != null) {
            dto.setDoctorName(patient.getDoctor().getName());
        }
        return dto;
    }

    // RequestDTO → Entity
    public PatientEntity toEntity(PatientRequestDTO dto) {
        PatientEntity patient = new PatientEntity();
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setAge(dto.getAge());
        patient.setDisease(dto.getDisease());
        patient.setAdmitDate(dto.getAdmitDate());
        patient.setSerious(dto.getSerious());
        return patient;
    }

    // List conversion
    public List<PatientResponseDTO> toResponseDTOList(
            List<PatientEntity> patients) {
        return patients.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<PatientResponseDTO> toResponseDTOList(
            List<PatientEntity> patients, PatientMapper mapper) {
        return patients.stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}