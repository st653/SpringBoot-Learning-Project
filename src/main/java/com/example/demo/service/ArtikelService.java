package com.example.demo.service;

import com.example.demo.entity.Artikel;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ArtikelService {

    private static final Logger logger = LoggerFactory.getLogger(ArtikelService.class);
    @Autowired
    private final ArtikelRepository artikelRepository;

    public ArtikelService(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }

    public Artikel erstelleArtikel(Artikel artikel) {
        return artikelRepository.save(artikel);
    }

    public List<Artikel> alleArtikelAbrufen() {
        return artikelRepository.findAll();
    }

    public Artikel findeArtikelNachId(Long id) {
        return artikelRepository.findById(id).orElse(null);
    }

    public void loescheArtikel(Long id) {
        artikelRepository.deleteById(id);
    }

    public void updateArtikel(Long id, Artikel updatedArtikel) {
        artikelRepository.findById(id)
                .map(artikel -> {
                    // Alten Zustand anzeigen
                    logger.info("Alter Zustand des Artikels: Name={}, Preis={}, Bestand={}, Gewicht={}",
                            artikel.getArticleName(), artikel.getPrice(), artikel.getStock(), artikel.getWeight());

                    // Artikel aktualisieren
                    artikel.setArticleName(updatedArtikel.getArticleName());
                    artikel.setPrice(updatedArtikel.getPrice());
                    artikel.setStock(updatedArtikel.getStock());
                    artikel.setWeight(updatedArtikel.getWeight());

                    // Neuen Zustand anzeigen
                    logger.info("Neuer Zustand des Artikels: Name={}, Preis={}, Bestand={}, Gewicht={}",
                            artikel.getArticleName(), artikel.getPrice(), artikel.getStock(), artikel.getWeight());

                    return artikelRepository.save(artikel);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Artikel nicht gefunden"));
    }

    public Artikel patchArtikel(Long id, Artikel artikelDetails) {
        return artikelRepository.findById(id)
                .map(artikel -> {
                    if (artikelDetails.getArticleName() != null) {
                        artikel.setArticleName(artikelDetails.getArticleName());
                    }
                    if (artikelDetails.getStock() != 0) {
                        artikel.setStock(artikelDetails.getStock());
                    }
                    if (artikelDetails.getPrice() != 0) {
                        artikel.setPrice(artikelDetails.getPrice());
                    }
                    if (artikelDetails.getWeight() != 0) {
                        artikel.setWeight(artikelDetails.getWeight());
                    }
                    return artikelRepository.save(artikel);
                })
                .orElseThrow(() -> new RuntimeException("Artikel nicht gefunden"));
    }

}
