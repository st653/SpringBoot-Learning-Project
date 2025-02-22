package com.example.demo.controller;

import com.example.demo.entity.Kunde;
import com.example.demo.service.KundeService;
import com.example.demo.validation.KundeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kunden")
public class KundeController {
    @Autowired
    private final KundeService kundeService;

    public KundeController(KundeService kundeService) {
        this.kundeService = kundeService;
    }

    @PostMapping
    public ResponseEntity<Kunde> neuerKunde(@RequestBody Kunde kunde) {
        System.out.println("New customer Post Request with name: " + kunde.getName());
        try {
            KundeValidator.validatePostInputParameter(kunde);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
            return null;
        }
        kundeService.erstelleKunde(kunde);
        return ResponseEntity.ok(kunde);
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
    public ResponseEntity<String> loescheKunde(@PathVariable Long id) {
        System.out.println("New Delete Request for customer with id: " + id);
        kundeService.loescheKunde(id);
        return ResponseEntity.ok("Kunde mit ID " + id + " wurde gelöscht.");
    }

    @PutMapping("")
    public ResponseEntity<String> updateKunde(@RequestBody Kunde updatedKunde) {
        System.out.println("New Put Request for customer with id: " + updatedKunde.getId());
        try {
            KundeValidator.validateUpdatedInputParameter(updatedKunde);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        kundeService.updateKunde(updatedKunde);
        return ResponseEntity.ok("Kunde aktualisiert.");
    }

    @PatchMapping("")
    public ResponseEntity<String> patchKunde(@RequestBody Kunde updatedKunde) {
        System.out.println("New Patch Request for customer with id: " + updatedKunde.getId());
        try {
            KundeValidator.validateUpdatedInputParameter(updatedKunde);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        kundeService.patchKunde(updatedKunde);
        return ResponseEntity.ok("Kunde aktualisiert.");
    }
}
