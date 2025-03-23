package com.example.demo.controller;

import com.example.demo.entity.Artikel;
import com.example.demo.service.ArtikelService;
import com.example.demo.validation.ArtikelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artikel")
public class ArtikelController {

    @Autowired
    private final ArtikelService artikelService;

    public ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }

    @PostMapping
    public ResponseEntity<Artikel> neuerArtikel(@RequestBody Artikel artikel) {
        System.out.println("New article Post Request with articleName: " + artikel.getArticleName());
        try {
            ArtikelValidator.validatePostInputParameter(artikel);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
        artikelService.erstelleArtikel(artikel);
        return ResponseEntity.ok(artikel);
    }

    @GetMapping
    public List<Artikel> alleArtikel() {
        System.out.println("New Get Request for all articles");
        return artikelService.alleArtikelAbrufen();
    }

    @GetMapping("/{id}")
    public Artikel artikelNachId(@PathVariable Long id) {
        System.out.println("New Get Request for article with id: " + id);
        return artikelService.findeArtikelNachId(id); //Fehlerbehandlung fehlt
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> loescheArtikel(@PathVariable Long id) {
        System.out.println("New Delete Request for article with id: " + id);
        artikelService.loescheArtikel(id);
        return ResponseEntity.ok("Artikel mit ID " + id + " wurde gelöscht.");
    }

    @PutMapping("")
    public ResponseEntity<String> updateArtikel(@RequestBody Artikel updatedArtikel) {
        System.out.println("New Put Request for article with id: " + updatedArtikel.getId());
        try {
            ArtikelValidator.validateUpdatedInputParameter(updatedArtikel);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        artikelService.updateArtikel(updatedArtikel);
        return ResponseEntity.ok("Artikel aktualisiert.");
    }

    @PatchMapping("")
    public ResponseEntity<String> patchArtikel(@RequestBody Artikel updatedArtikel) {
        System.out.println("New Patch Request for article with id: " + updatedArtikel.getId());
        try {
            ArtikelValidator.validateUpdatedInputParameter(updatedArtikel);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        artikelService.patchArtikel(updatedArtikel);
        return ResponseEntity.ok("Artikel aktualisiert.");
    }

    @GetMapping("/ohne-Bestand")
    public List<Artikel> findeArtikelOhneBestand (){
        return artikelService.findeArtikelMitKeinemLagerbestand();
    }

    @GetMapping("/bestand-bereich")
    public List<Artikel> findeArtikelMitBestandBereich(@RequestParam int minStock, @RequestParam int maxStock) {
        return artikelService.findeArtikelMitLagerbestandZwischen(minStock, maxStock);
    }
}
