package com.example.demo.service;

import com.example.demo.entity.Kunde;
import com.example.demo.repository.KundeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KundeService {
    private final KundeRepository kundeRepository;

    public KundeService(KundeRepository kundeRepository) {
        this.kundeRepository = kundeRepository;
    }

    public Kunde erstelleKunde(String name) {
        Kunde kunde = new Kunde(name);
        return kundeRepository.save(kunde);
    }

    public List<Kunde> alleKundenAbrufen() {
        return kundeRepository.findAll();
    }

    public Kunde findeKundeNachId(Long id) {
        return kundeRepository.findById(id).orElse(null);
    }

    public void loescheKunde(Long id) {
        kundeRepository.deleteById(id);
    }
}