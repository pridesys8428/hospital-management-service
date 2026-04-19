package com.example.hospitalmanagement.hospital.management.repository;

import com.example.hospitalmanagement.hospital.management.model.Appointment;
import com.example.hospitalmanagement.hospital.management.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}