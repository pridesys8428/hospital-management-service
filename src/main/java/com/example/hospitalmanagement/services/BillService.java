package com.example.hospitalmanagement.services;

import com.example.hospitalmanagement.model.Bill;
import com.example.hospitalmanagement.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public List<Bill> getAllBills() {
        try {
            System.out.println("Get all bills here");
            return billRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error getting bills: " + e);
            return null;
        }
    }

    public Bill addNewBill(Bill bill) {
        try {
            return billRepository.save(bill);
        } catch (Exception e) {
            System.out.println("Error saving bill: " + e);
            return null;
        }
    }

    public Optional<Bill> getById(Long id) {
        try {
            return billRepository.findById(id);
        } catch (Exception e) {
            System.out.println("Error getting bill: " + e);
            return Optional.empty();
        }
    }

    public void deleteBillById(Long id) {
        try {
            billRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error deleting bill: " + e);
        }
    }

    public Bill updateBill(Long id, Bill updatedBill) {
        try {
            return billRepository.findById(id)
                    .map(existingBill -> {
                        // Update fields
                        existingBill.setPatientId(updatedBill.getPatientId());
                        existingBill.setAmount(updatedBill.getAmount());
                        existingBill.setStatus(updatedBill.getStatus());

                        // Save and return updated bill
                        return billRepository.save(existingBill);
                    })
                    .orElseThrow(() -> new RuntimeException("Bill not found with id " + id));
        } catch (Exception e) {
            System.out.println("Error updating bill: " + e);
            return null;
        }
    }
}
