package com.hospital.Hospital.Management.System.Services;

import com.hospital.Hospital.Management.System.Entity.HospitalManagement;
import org.springframework.stereotype.Service;

@Service
public class HospitalServices {

    private final DoctorServices doctorServices;
    private final PatientServices patientServices;

    public HospitalServices(DoctorServices doctorServices,
                            PatientServices patientServices) {
        this.doctorServices = doctorServices;
        this.patientServices = patientServices;
    }

    public HospitalManagement getHospitalStats() {

        HospitalManagement stats = new HospitalManagement();

        stats.setTotalDoctors(doctorServices.countAll());
        stats.setAvailableDoctors(doctorServices.countAvailable());
        stats.setTotalPatients(patientServices.countAll());
        stats.setSeriousPatients(patientServices.countSerious());

        return stats;
    }
}