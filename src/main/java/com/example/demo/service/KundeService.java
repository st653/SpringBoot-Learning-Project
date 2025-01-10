package com.example.demo.service;

import com.example.demo.entity.Kunde;
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

    public Kunde erstelleKunde(String name) {
        Kunde kunde = new Kunde(name);
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
}