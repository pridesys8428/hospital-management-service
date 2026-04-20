package com.example.hospitalmanagement.controllers;

import com.example.hospitalmanagement.model.Appointment;
import com.example.hospitalmanagement.model.IdRequest;
import com.example.hospitalmanagement.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/all")
    public List<Appointment> getAllAppointment(){

        return appointmentService.getAllAppointments();
    }

    @PostMapping("/add")
    public Appointment createAppointment(@RequestBody Appointment appointment){
        return appointmentService.addNewAppointment(appointment);
    }

    @PostMapping("/get")
    public Optional<Appointment> getAppointmentById(@RequestBody IdRequest idRequest){
        return  appointmentService.getById(idRequest.getId());
    }

    @PostMapping("/delete")
    public void deleteAppointment(@RequestBody IdRequest idRequest){
        appointmentService.deleteAppointmentById(idRequest.getId());
    }

    @PostMapping("/update")
    public Appointment updateAppointment(@RequestBody Appointment appointment){
        return appointmentService.updateAppointment(appointment.getId(), appointment);
    }


}
