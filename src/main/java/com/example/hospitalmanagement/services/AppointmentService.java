package com.example.hospitalmanagement.services;

import com.example.hospitalmanagement.model.Appointment;
import com.example.hospitalmanagement.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments(){
        try{
            System.out.println("Get all patient here");

            return appointmentRepository.findAll();
        }catch (Exception e){
            System.out.println("Error getting data");
            return null;
        }
    }

    public Appointment addNewAppointment(Appointment appointment) {
        try{
            return appointmentRepository.save(appointment);
        }catch (Exception e){
            System.out.println("error saving data"+e);
            return null;
        }
    }


    public Optional<Appointment> getById(Long id) {
        try {
            return appointmentRepository.findById(id);
        }catch (Exception e){
            System.out.println("error getting data"+e);
            return Optional.empty();
        }
    }

    public void deleteAppointmentById(Long id) {
        // delete here
        try{
            appointmentRepository.deleteById(id);
        }catch (Exception e){
            System.out.println("error deleting data"+e);
        }
    }

    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        try {
            return appointmentRepository.findById(id)
                    .map(existingAppointment -> {
                        // Update fields
                        existingAppointment.setDate(updatedAppointment.getDate());
                        existingAppointment.setDoctorId(updatedAppointment.getDoctorId());
                        existingAppointment.setPatientId(updatedAppointment.getPatientId());
                        // add other fields you want to update

                        return appointmentRepository.save(existingAppointment);
                    })
                    .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
        } catch (Exception e) {
            System.out.println("Error updating data: " + e);
            return null;
        }
    }

}
