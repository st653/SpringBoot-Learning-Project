package com.example.demo.service;

import com.example.demo.entity.Artikel;
import com.example.demo.repository.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtikelService {

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

    public void updateArtikel(Long id, String articleName, Double price, Integer stock, Double weight) {
        Artikel artikel = artikelRepository.findById(id).orElse(null);
        if (artikel != null) {
            artikel.setArticleName(articleName);
            artikel.setPrice(price);
            artikel.setStock(stock);
            artikel.setWeight(weight);
            artikelRepository.save(artikel);
        }
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
