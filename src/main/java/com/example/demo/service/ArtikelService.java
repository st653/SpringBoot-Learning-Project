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
        logger.info("Artikeldetails: Name={}, Preis={}, Bestand={}, Gewicht={}",
                artikel.getArticleName(), artikel.getPrice(), artikel.getStock(), artikel.getWeight());
        return artikelRepository.save(artikel);
    }

    public List<Artikel> alleArtikelAbrufen() {
        List<Artikel> artikelListe = artikelRepository.findAll();

        if (artikelListe.isEmpty()) {
            logger.info("Keine Artikel gefunden.");
        } else {
            artikelListe.forEach(artikel ->
                    logger.info("Artikel ID: {}, Name: {}, Preis: {}, Bestand: {}, Gewicht: {}",
                            artikel.getId(), artikel.getArticleName(), artikel.getPrice(),
                            artikel.getStock(), artikel.getWeight()));
        }

        return artikelListe;
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

    public Artikel patchArtikel(Long id, Artikel updatedArtikel) {
        return artikelRepository.findById(id)
                .map(artikel -> {
                    // Alten Zustand anzeigen
                    logger.info("Alter Zustand des Artikels: Name={}, Preis={}, Bestand={}, Gewicht={}",
                            artikel.getArticleName(), artikel.getPrice(), artikel.getStock(), artikel.getWeight());

                    // Artikel aktualisieren
                    if (updatedArtikel.getArticleName() != null) {
                        artikel.setArticleName(updatedArtikel.getArticleName());
                    }
                    if (updatedArtikel.getStock() != null) {
                        artikel.setStock(updatedArtikel.getStock());
                    }
                    if (updatedArtikel.getPrice() != null) {
                        artikel.setPrice(updatedArtikel.getPrice());
                    }
                    if (updatedArtikel.getWeight() != null) {
                        artikel.setWeight(updatedArtikel.getWeight());
                    }

                    // Neuen Zustand anzeigen
                    logger.info("Neuer Zustand des Artikels: Name={}, Preis={}, Bestand={}, Gewicht={}",
                            artikel.getArticleName(), artikel.getPrice(), artikel.getStock(), artikel.getWeight());

                    return artikelRepository.save(artikel);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Artikel nicht gefunden"));
    }

}
