package com.matrixx.billing.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingController {
    @GetMapping("/matrixx/billing")
    public String callOtherService(@RequestParam String mode) {
        return "Welcome to Matrixx Billing ";
    }
}
