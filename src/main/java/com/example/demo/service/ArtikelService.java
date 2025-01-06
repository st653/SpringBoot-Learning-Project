package com.example.demo.service;

import com.example.demo.entity.Artikel;
import com.example.demo.repository.ArtikelRepository;
import org.springframework.stereotype.Service;

@Service
public class ArtikelService {

    private final ArtikelRepository artikelRepository;

    public ArtikelService(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }

    public Artikel erstelleArtikel(String articleName, double price, int stock, double weight) {
        Artikel artikel = new Artikel(articleName, price, stock, weight);
        return artikelRepository.save(artikel);
    }

    public Iterable<Artikel> alleArtikelAbrufen() {
        return artikelRepository.findAll();
    }

    public Artikel findeArtikelNachId(Long id) {
        return artikelRepository.findById(id).orElse(null);
    }

    public void loescheArtikel(Long id) {
        artikelRepository.deleteById(id);
    }

}
