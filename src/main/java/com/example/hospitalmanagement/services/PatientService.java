package com.example.hospitalmanagement.services;

import com.example.hospitalmanagement.model.Patient;
import com.example.hospitalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        try {
            System.out.println("Get all patients here");
            return patientRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error getting patients: " + e);
            return null;
        }
    }

    public Patient addNewPatient(Patient patient) {
        try {
            return patientRepository.save(patient);
        } catch (Exception e) {
            System.out.println("Error saving patient: " + e);
            return null;
        }
    }

    public Optional<Patient> getById(Long id) {
        try {
            return patientRepository.findById(id);
        } catch (Exception e) {
            System.out.println("Error getting patient: " + e);
            return Optional.empty();
        }
    }

    public void deletePatentById(Long id) {
        try {
            patientRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error deleting patient: " + e);
        }
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        try {
            return patientRepository.findById(id)
                    .map(existingPatient -> {
                        // update fields
                        existingPatient.setName(updatedPatient.getName());
                        existingPatient.setGender(updatedPatient.getGender());
                        existingPatient.setAge(updatedPatient.getAge());

                        // save and return updated patient
                        return patientRepository.save(existingPatient);
                    })
                    .orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
        } catch (Exception e) {
            System.out.println("Error updating patient: " + e);
            return null;
        }
    }
}
