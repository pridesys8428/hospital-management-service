package com.example.hospitalmanagement.hospital.management.services;

import com.example.hospitalmanagement.hospital.management.model.Doctor;
import com.example.hospitalmanagement.hospital.management.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        try {
            System.out.println("Get all doctors here");
            return doctorRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error getting doctors: " + e);
            return null;
        }
    }

    public Doctor addNewDoctor(Doctor doctor) {
        try {
            return doctorRepository.save(doctor);
        } catch (Exception e) {
            System.out.println("Error saving doctor: " + e);
            return null;
        }
    }

    public Optional<Doctor> getById(Long id) {
        try {
            return doctorRepository.findById(id);
        } catch (Exception e) {
            System.out.println("Error getting doctor: " + e);
            return Optional.empty();
        }
    }

    public void deleteDoctorById(Long id) {
        try {
            doctorRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error deleting doctor: " + e);
        }
    }

    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        try {
            return doctorRepository.findById(id)
                    .map(existingDoctor -> {
                        // update fields
                        existingDoctor.setName(updatedDoctor.getName());
                        existingDoctor.setGender(updatedDoctor.getGender());
                        existingDoctor.setSpeciality(updatedDoctor.getSpeciality());
                        existingDoctor.setShiftTime(updatedDoctor.getShiftTime());
                        existingDoctor.setAge(updatedDoctor.getAge());

                        // save and return updated doctor
                        return doctorRepository.save(existingDoctor);
                    })
                    .orElseThrow(() -> new RuntimeException("Doctor not found with id " + id));
        } catch (Exception e) {
            System.out.println("Error updating doctor: " + e);
            return null;
        }
    }
}
