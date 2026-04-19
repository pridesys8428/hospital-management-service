package com.example.hospitalmanagement.hospital.management.controllers;

import com.example.hospitalmanagement.hospital.management.model.Doctor;
import com.example.hospitalmanagement.hospital.management.model.IdRequest;
import com.example.hospitalmanagement.hospital.management.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/all")
    public List<Doctor> getAllDoctor(){

        return doctorService.getAllDoctors();
    }

    @PostMapping("/add")
    public Doctor createDoctor(@RequestBody Doctor doctor){
        return doctorService.addNewDoctor(doctor);
    }

    @PostMapping("/get")
    public Optional<Doctor> getDoctorById(@RequestBody IdRequest idRequest){
        return  doctorService.getById(idRequest.getId());
    }

    @PostMapping("/delete")
    public void deleteDoctor(@RequestBody IdRequest idRequest){
        doctorService.deleteDoctorById(idRequest.getId());
    }

    @PostMapping("/update")
    public Doctor updateDoctor(@RequestBody Doctor doctor){
        return doctorService.updateDoctor(doctor.getId(), doctor);
    }


}
