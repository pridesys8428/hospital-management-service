package com.example.hospitalmanagement.hospital.management.controllers;

import com.example.hospitalmanagement.hospital.management.model.Bill;
import com.example.hospitalmanagement.hospital.management.model.IdRequest;
import com.example.hospitalmanagement.hospital.management.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    @Autowired
    private BillService BillService;

    @PostMapping("/all")
    public List<Bill> getAllBill(){

        return BillService.getAllBills();
    }

    @PostMapping("/add")
    public Bill createBill(@RequestBody Bill bill){
        return BillService.addNewBill(bill);
    }

    @PostMapping("/get")
    public Optional<Bill> getBillById(@RequestBody IdRequest idRequest){
        return  BillService.getById(idRequest.getId());
    }

    @PostMapping("/delete")
    public void deleteBill(@RequestBody IdRequest idRequest){
        BillService.deleteBillById(idRequest.getId());
    }

    @PostMapping("/update")
    public Bill updateBill(@RequestBody Bill bill){
        return BillService.updateBill(bill.getId(), bill);
    }


}
