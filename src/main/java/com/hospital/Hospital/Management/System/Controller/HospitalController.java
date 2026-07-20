package com.hospital.Hospital.Management.System.Controller;

import com.hospital.Hospital.Management.System.Entity.HospitalManagement;
import com.hospital.Hospital.Management.System.Services.DoctorServices;
import com.hospital.Hospital.Management.System.Services.PatientServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    private final DoctorServices doctorServices;
    private final PatientServices patientServices;

    public HospitalController(DoctorServices doctorServices,
                              PatientServices patientServices) {
        this.doctorServices = doctorServices;
        this.patientServices = patientServices;
    }

    @GetMapping("/stats")
    public ResponseEntity<HospitalManagement> getStats() {
        HospitalManagement stats = new HospitalManagement();
        stats.setTotalDoctors(doctorServices.countAll());
        stats.setAvailableDoctors(doctorServices.countAvailable());
        stats.setTotalPatients(patientServices.countAll());
        stats.setSeriousPatients(patientServices.countSerious());
        return ResponseEntity.ok(stats);
    }
}