package com.example.demo.controller;

import com.example.demo.entity.Kunde;
import com.example.demo.service.KundeService;
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
        return kundeService.erstelleKunde(name);
    }

    @GetMapping
    public List<Kunde> alleKunden() {
        return kundeService.alleKundenAbrufen();
    }

    @GetMapping("/{id}")
    public Kunde kundeNachId(@PathVariable Long id) {
        return kundeService.findeKundeNachId(id);
    }

    @DeleteMapping("/{id}")
    public String loescheKunde(@PathVariable Long id) {
        kundeService.loescheKunde(id);
        return "Kunde mit ID " + id + " wurde gelöscht.";
    }
}
