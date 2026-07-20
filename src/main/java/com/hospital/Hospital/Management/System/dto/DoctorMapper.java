package com.hospital.Hospital.Management.System.dto;

import com.hospital.Hospital.Management.System.Entity.DoctorEntity;
import com.hospital.Hospital.Management.System.Entity.PatientEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component  // Spring manages this as a bean
public class DoctorMapper {

    // Entity → ResponseDTO (for GET responses)
    public DoctorResponseDTO toResponseDTO(DoctorEntity doctor) {
        DoctorResponseDTO dto = new DoctorResponseDTO();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setEmail(doctor.getEmail());
        dto.setPhone(doctor.getPhone());
        dto.setAvailable(doctor.getAvailable());
        return dto;
    }

    // Entity → DoctorWithPatientsDTO (for GET /doctors/{id})
    public DoctorWithPatientsDTO toDoctorWithPatientsDTO(
            DoctorEntity doctor,
            PatientMapper patientMapper) {

        DoctorWithPatientsDTO dto = new DoctorWithPatientsDTO();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setEmail(doctor.getEmail());
        dto.setAvailable(doctor.getAvailable());

        // Convert patient list too
        List<PatientResponseDTO> patients = doctor.getPatients() == null
                ? List.of()
                : doctor.getPatients().stream()
                .map(patientMapper::toResponseDTO)
                .collect(Collectors.toList());
        dto.setPatients(patients);
        return dto;
    }

    // RequestDTO → Entity (for POST/PUT)
    public DoctorEntity toEntity(DoctorRequestDTO dto) {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setEmail(dto.getEmail());
        doctor.setPhone(dto.getPhone());
        doctor.setAvailable(dto.getAvailable());
        return doctor;
    }

    // List of entities → List of DTOs
    public List<DoctorResponseDTO> toResponseDTOList(
            List<DoctorEntity> doctors) {
        return doctors.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}