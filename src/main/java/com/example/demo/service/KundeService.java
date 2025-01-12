package com.example.demo.service;

import com.example.demo.entity.Kunde;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.KundeRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class KundeService {

    private static final Logger logger = LoggerFactory.getLogger(ArtikelService.class);

    @Autowired
    private final KundeRepository kundeRepository;

    public KundeService(KundeRepository kundeRepository) {
        this.kundeRepository = kundeRepository;
    }

    public Kunde erstelleKunde(Kunde kunde) {
        logger.info ("Kundendetails: Name={}", kunde.getName());
        return kundeRepository.save(kunde);
    }

    public List<Kunde> alleKundenAbrufen() {
        List<Kunde> kundenListe = kundeRepository.findAll();

        if(kundenListe.isEmpty()) {
            logger.info("Keine Kunden gefunden.");
        } else {
            kundenListe.forEach(kunde ->
                logger.info("Kunde ID: {}, Name: {}", kunde.getId(), kunde.getName()));
        }
        return kundeRepository.findAll();
    }

    public Kunde findeKundeNachId(Long id) {
        return kundeRepository.findById(id).orElse(null);
    }

    public void loescheKunde(Long id) {
        kundeRepository.deleteById(id);
    }

    public void updateKunde(Long id, Kunde updatedKunde) {
        kundeRepository.findById(id)
                .map(kunde -> {
                    // Alten Zustand anzeigen
                    logger.info("Alter Zustand des Kunden: Name={}", kunde.getName());

                    // Kunde aktualisieren
                    kunde.setName(updatedKunde.getName());
                    Kunde neuerKunde = kundeRepository.save(kunde);

                    // Neuen Zustand anzeigen
                    logger.info("Neuer Zustand des Kunden: Name={}", neuerKunde.getName());
                    return neuerKunde;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Kunde nicht gefunden mit ID " + id));
    }

    public void patchKunde(Long id, Kunde updatedKunde) {
        kundeRepository.findById(id)
                .map(kunde -> {
                    // Alten Zustand anzeigen
                    logger.info("Alter Zustand des Kunden: Name={}", kunde.getName());

                    // Kunde aktualisieren
                    if (updatedKunde.getName() != null) {
                        kunde.setName(updatedKunde.getName());
                    }
                    Kunde neuerKunde = kundeRepository.save(kunde);

                    // Neuen Zustand anzeigen
                    logger.info("Neuer Zustand des Kunden: Name={}", neuerKunde.getName());
                    return neuerKunde;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Kunde nicht gefunden mit ID " + id));
    }
}