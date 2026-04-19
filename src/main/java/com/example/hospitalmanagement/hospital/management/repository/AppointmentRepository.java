package com.example.hospitalmanagement.hospital.management.repository;

import com.example.hospitalmanagement.hospital.management.model.Appointment;
import com.example.hospitalmanagement.hospital.management.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}