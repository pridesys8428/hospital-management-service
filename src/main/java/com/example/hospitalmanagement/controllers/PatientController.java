package com.example.hospitalmanagement.controllers;

import com.example.hospitalmanagement.model.IdRequest;
import com.example.hospitalmanagement.model.Patient;
import com.example.hospitalmanagement.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/all")
    public List<Patient> getAllPatient(){

        return patientService.getAllPatients();
    }

    @PostMapping("/add")
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.addNewPatient(patient);
    }

    @PostMapping("/get")
    public Optional<Patient> getPatientById(@RequestBody IdRequest idRequest){
        return  patientService.getById(idRequest.getId());
    }

    @PostMapping("/delete")
    public void deletePatient(@RequestBody IdRequest idRequest){
        patientService.deletePatentById(idRequest.getId());
    }

    @PostMapping("/update")
    public Patient updatePatient(@RequestBody Patient patient){
        return patientService.updatePatient(patient.getId(), patient);
    }


}
