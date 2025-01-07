package com.example.demo.controller;

import com.example.demo.entity.Artikel;
import com.example.demo.service.ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Artikel neuerArtikel(@RequestBody Artikel artikel) {
        System.out.println("New Post Request with articleName: " + artikel.getArticleName());
        return artikelService.erstelleArtikel(artikel);
    }

    @GetMapping
    public List<Artikel> alleArtikel() {
        System.out.println("New Get Request for all articles");
        return artikelService.alleArtikelAbrufen();
    }

    @GetMapping("/{id}")
    public Artikel artikelNachId(@PathVariable Long id) {
        System.out.println("New Get Request for article with id: " + id);
        return artikelService.findeArtikelNachId(id);
    }

    @DeleteMapping("/{id}")
    public String loescheArtikel(@PathVariable Long id) {
        System.out.println("New Delete Request for article with id: " + id);
        artikelService.loescheArtikel(id);
        return "Artikel mit ID " + id + " wurde gelöscht.";
    }

    @PutMapping("/{id}")
    public String updateArtikel(@PathVariable Long id, @RequestBody Artikel artikel) {
        System.out.println("New Put Request for article with id: " + id);
        artikelService.updateArtikel(id, artikel);
        return "Artikel mit ID " + id + " wurde aktualisiert.";
    }

    @PatchMapping("/{id}")
    public String patchArtikel(@PathVariable Long id, Artikel artikelDetails) {
        System.out.println("New Patch Request for article with id: " + id);
        artikelService.patchArtikel(id, artikelDetails);
        return "Artikel mit ID " + id + " wurde aktualisiert.";
    }

}
