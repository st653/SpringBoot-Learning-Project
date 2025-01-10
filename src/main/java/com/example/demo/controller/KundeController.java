package com.example.demo.controller;

import com.example.demo.entity.Kunde;
import com.example.demo.service.KundeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kunden")
public class KundeController {
    private final KundeService kundeService;

    public KundeController(KundeService kundeService) {
        this.kundeService = kundeService;
    }

    @PostMapping
    public Kunde neuerKunde(@RequestParam String name) {
        System.out.println("New Post Request with name: " + name);
        return kundeService.erstelleKunde(name);
    }

    @GetMapping
    public List<Kunde> alleKunden() {
        System.out.println("New Get Request for all customers");
        return kundeService.alleKundenAbrufen();
    }

    @GetMapping("/{id}")
    public Kunde kundeNachId(@PathVariable Long id) {
        System.out.println("New Get Request for customer with id: " + id);
        return kundeService.findeKundeNachId(id);
    }

    @DeleteMapping("/{id}")
    public String loescheKunde(@PathVariable Long id) {
        System.out.println("New Delete Request for customer with id: " + id);
        kundeService.loescheKunde(id);
        return "Kunde mit ID " + id + " wurde gelöscht.";
    }
}
