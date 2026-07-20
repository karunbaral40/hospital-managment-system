package com.hospital.Hospital.Management.System.Entity;

public class HospitalManagement {
    private long totalDoctors;
    private long availableDoctors;
    private long totalPatients;
    private long seriousPatients;

    public HospitalManagement() {}

    public HospitalManagement(long totalDoctors, long availableDoctors, long totalPatients, long seriousPatients) {
        this.totalDoctors = totalDoctors;
        this.availableDoctors = availableDoctors;
        this.totalPatients = totalPatients;
        this.seriousPatients = seriousPatients;
    }

    public long getTotalDoctors() {
        return totalDoctors;
    }

    public void setTotalDoctors(long totalDoctors) {
        this.totalDoctors = totalDoctors;
    }

    public long getAvailableDoctors() {
        return availableDoctors;
    }

    public void setAvailableDoctors(long availableDoctors) {
        this.availableDoctors = availableDoctors;
    }

    public long getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(long totalPatients) {
        this.totalPatients = totalPatients;
    }

    public long getSeriousPatients() {
        return seriousPatients;
    }

    public void setSeriousPatients(long seriousPatients) {
        this.seriousPatients = seriousPatients;
    }
}