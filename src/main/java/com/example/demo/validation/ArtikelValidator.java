package com.example.demo.validation;

import com.example.demo.entity.Artikel;
import org.springframework.stereotype.Component;

@Component
public class ArtikelValidator {

    public static void validatePostInputParameter (Artikel artikel) {
        if (artikel.getArticleName() == null || artikel.getArticleName().isEmpty()) {
            throw new IllegalArgumentException("Artikelname darf nicht leer sein.");
        }
        if (artikel.getPrice() == null || artikel.getPrice() < 0) {
            throw new IllegalArgumentException("Preis darf nicht leer oder negativ sein.");
        }
        if (artikel.getStock() == null || artikel.getStock() < 0) {
            throw new IllegalArgumentException("Bestand darf nicht leer oder negativ sein.");
        }
        if (artikel.getWeight() == null || artikel.getWeight() < 0) {
            throw new IllegalArgumentException("Gewicht darf nicht leer oder negativ sein.");
        }
        System.out.println("Validation successful");
    }

    public static void validateUpdatedInputParameter (Artikel updatedArtikel) {
        if (updatedArtikel.getId() == null) {
            throw new IllegalArgumentException("Die Artikel-ID darf nicht null sein.");
        }
        if (updatedArtikel.getArticleName() != null && updatedArtikel.getArticleName().trim().isEmpty()) {
            throw new IllegalArgumentException("Der Artikelname darf nicht leer sein, wenn er gesetzt ist.");
        }
        if (updatedArtikel.getWeight() != null && updatedArtikel.getWeight() < 0) {
            throw new IllegalArgumentException("Das Gewicht muss größer oder gleich 0 sein.");
        }
        if (updatedArtikel.getPrice() != null && updatedArtikel.getPrice() < 0) {
            throw new IllegalArgumentException("Der Preis muss größer oder gleich 0 sein.");
        }
        if (updatedArtikel.getStock() != null && updatedArtikel.getStock() < 0) {
            throw new IllegalArgumentException("Der Bestand muss größer oder gleich 0 sein.");
        }
        System.out.println("Validation successful");
    }
}
