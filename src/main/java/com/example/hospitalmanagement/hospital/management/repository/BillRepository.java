package com.example.hospitalmanagement.hospital.management.repository;

import com.example.hospitalmanagement.hospital.management.model.Bill;
import com.example.hospitalmanagement.hospital.management.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}