package com.example.demo.validation;

import com.example.demo.entity.Artikel;
import org.springframework.stereotype.Component;

@Component
public class ArtikelValidator {

    public static final String ARTIKELNAME_DARF_NICHT_LEER_SEIN = "Artikelname darf nicht leer sein.";
    public static final String PREIS_DARF_NICHT_LEER_ODER_NEGATIV_SEIN = "Preis darf nicht leer oder negativ sein.";
    public static final String BESTAND_DARF_NICHT_LEER_ODER_NEGATIV_SEIN = "Bestand darf nicht leer oder negativ sein.";
    public static final String GEWICHT_DARF_NICHT_LEER_ODER_NEGATIV_SEIN = "Gewicht darf nicht leer oder negativ sein.";
    public static final String ARTIKEL_ID_DARF_NICHT_NULL_SEIN = "Die Artikel-ID darf nicht null sein.";
    public static final String ARTIKELNAME_DARF_NICHT_LEER_SEIN_WENN_ER_GESETZT_IST = "Artikelname darf nicht leer sein, wenn er gesetzt ist.";
    public static final String GEWICHT_MUSS_GROESSER_ODER_GLEICH_0_SEIN = "Gewicht muss groeßer oder gleich 0 sein.";
    public static final String PREIS_MUSS_GROESSER_ODER_GLEICH_0_SEIN = "Preis muss groesser oder gleich 0 sein.";
    public static final String BESTAND_MUSS_GROESSER_ODER_GLEICH_0_SEIN = "Bestand muss groesser oder gleich 0 sein.";

    public static void validatePostInputParameter (Artikel artikel) {
        if (artikel.getArticleName() == null || artikel.getArticleName().isEmpty()) {
            throw new IllegalArgumentException(ARTIKELNAME_DARF_NICHT_LEER_SEIN);
        }
        if (artikel.getPrice() == null || artikel.getPrice() < 0) {
            throw new IllegalArgumentException(PREIS_DARF_NICHT_LEER_ODER_NEGATIV_SEIN);
        }
        if (artikel.getStock() == null || artikel.getStock() < 0) {
            throw new IllegalArgumentException(BESTAND_DARF_NICHT_LEER_ODER_NEGATIV_SEIN);
        }
        if (artikel.getWeight() == null || artikel.getWeight() < 0) {
            throw new IllegalArgumentException(GEWICHT_DARF_NICHT_LEER_ODER_NEGATIV_SEIN);
        }
        System.out.println("Validation successful");
    }

    public static void validateUpdatedInputParameter (Artikel updatedArtikel) {
        if (updatedArtikel.getId() == null) {
            throw new IllegalArgumentException(ARTIKEL_ID_DARF_NICHT_NULL_SEIN);
        }
        if (updatedArtikel.getArticleName() != null && updatedArtikel.getArticleName().trim().isEmpty()) {
            throw new IllegalArgumentException(ARTIKELNAME_DARF_NICHT_LEER_SEIN_WENN_ER_GESETZT_IST);
        }
        if (updatedArtikel.getWeight() != null && updatedArtikel.getWeight() < 0) {
            throw new IllegalArgumentException(GEWICHT_MUSS_GROESSER_ODER_GLEICH_0_SEIN);
        }
        if (updatedArtikel.getPrice() != null && updatedArtikel.getPrice() < 0) {
            throw new IllegalArgumentException(PREIS_MUSS_GROESSER_ODER_GLEICH_0_SEIN);
        }
        if (updatedArtikel.getStock() != null && updatedArtikel.getStock() < 0) {
            throw new IllegalArgumentException(BESTAND_MUSS_GROESSER_ODER_GLEICH_0_SEIN);
        }
        System.out.println("Validation successful");
    }
}
