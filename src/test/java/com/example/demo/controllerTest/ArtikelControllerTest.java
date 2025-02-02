package com.example.demo.controllerTest;

import com.example.demo.controller.ArtikelController;
import com.example.demo.entity.Artikel;
import com.example.demo.service.ArtikelService;
import com.example.demo.validation.ArtikelValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArtikelControllerTest {

    @Mock
    private ArtikelService artikelService;

    @InjectMocks
    private ArtikelController artikelController;

    private Artikel artikel;

    @BeforeEach
    void setUp() {
        artikel = new Artikel("Laptop", 500.0, 5, 2.0);
    }

    // Checks if a new, valid article is saved with http 200 ok
    @Test
    void neuerArtikel_ValidInput_ShouldReturnOk() {
        //given

        //when

        //act
        ResponseEntity<String> response = artikelController.neuerArtikel(artikel);

        //assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Neuer Artikel erstellt"));

        verify(artikelService, times(1)).erstelleArtikel(any(Artikel.class));
    }

    // Checks if a new, invalid article returns http 400 bad request and is not saved
    @Test
    void neuerArtikel_InvalidInput_nameIsNull_ShouldReturnBadRequest() {
        //given
        artikel.setArticleName(null);
        //when

        //act
        ResponseEntity<String> response = artikelController.neuerArtikel(artikel);

        //assert
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().contains(ArtikelValidator.ARTIKELNAME_DARF_NICHT_LEER_SEIN));

        verify(artikelService, never()).erstelleArtikel(any(Artikel.class));
    }

    // Checks if all articles are returned
    @Test
    void shouldReturnAllArticles() {
        //given
        List<Artikel> artikelList = List.of(artikel);

        //when
        when(artikelService.alleArtikelAbrufen()).thenReturn(artikelList);

        //act
        List<Artikel> result = artikelController.alleArtikel();

        //assert
        assertEquals(1, result.size());
        verify(artikelService, times(1)).alleArtikelAbrufen();
    }

    // Checks if an article is returned by id
    @Test
    void shouldReturnArticleById() {
        //given
        artikel.setId(1L);

        //when
        when(artikelService.findeArtikelNachId(1L)).thenReturn(artikel);

        //act
        Artikel result = artikelController.artikelNachId(1L);

        //assert
        assertEquals(1L, result.getId());
        verify(artikelService, times(1)).findeArtikelNachId(1L);
    }

    // Checks if an article is deleted by id
    @Test
    void shouldDeleteArticleById() {
        //given
        artikel.setId(1L);

        //when

        //act
        ResponseEntity<String> response = artikelController.loescheArtikel(1L);

        //assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains(artikel.getId() + " wurde gelöscht"));

        verify(artikelService, times(1)).loescheArtikel(1L);
    }

    // Checks if an article is updated and returns ok
    @Test
    void shouldUpdateArticle_ShouldReturnOk() {
        //given
        Artikel updatedArtikel = new Artikel("Bildschirm", 600.0, 10, 2.0);
        updatedArtikel.setId(1L);

        //when

        //act
        ResponseEntity<String> response = artikelController.updateArtikel(updatedArtikel);

        //assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Artikel aktualisiert"));

        verify(artikelService, times(1)).updateArtikel(any(Artikel.class));
    }

    // Checks if article update returns bad request
    @Test
    void shouldNotUpdateArticle_returnsBadRequest() {
        //given
        Artikel updatedArtikel = new Artikel(null, -30.0, 10, 2.0);
        updatedArtikel.setId(1L);

        //when

        //act
        ResponseEntity<String> response = artikelController.updateArtikel(updatedArtikel);

        //assert
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().contains(ArtikelValidator.PREIS_MUSS_GROESSER_ODER_GLEICH_0_SEIN));

        verify(artikelService, never()).updateArtikel(any(Artikel.class));
    }

    // Checks if an article is patched and returns ok
    @Test
    void shouldPatchArticle_ShouldReturnOk() {
        //given
        Artikel updatedArtikel = new Artikel(null, null, 10, 2.0);
        updatedArtikel.setId(1L);

        //when

        //act
        ResponseEntity<String> response = artikelController.patchArtikel(updatedArtikel);

        //assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Artikel aktualisiert"));

        verify(artikelService, times(1)).patchArtikel(any(Artikel.class));
    }

    // Checks if article update returns bad request
    @Test
    void shouldNotPatchArticle_returnsBadRequest() {
        //given
        Artikel updatedArtikel = new Artikel(null, -30.0, 10, 2.0);
        updatedArtikel.setId(1L);

        //when

        //act
        ResponseEntity<String> response = artikelController.patchArtikel(updatedArtikel);

        //assert
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().contains(ArtikelValidator.PREIS_MUSS_GROESSER_ODER_GLEICH_0_SEIN));

        verify(artikelService, never()).updateArtikel(any(Artikel.class));
    }

    // Checks if an article with no stock is returned
    @Test
    void shouldReturnArticlesWithNoStock() {
        //given
        List<Artikel> artikelList = List.of(artikel);
        artikel.setStock(0);

        //when
        when(artikelService.findeArtikelMitKeinemLagerbestand()).thenReturn(artikelList);

        //act
        List<Artikel> result = artikelController.findeArtikelOhneBestand();

        //assert
        assertEquals(1, result.size());
        verify(artikelService, times(1)).findeArtikelMitKeinemLagerbestand();
    }

    // Checks if articles with stock between a range are returned
    @Test
    void shouldReturnArticlesWithStockBetween() {
        //given
        List<Artikel> artikelList = List.of(artikel);
        int minStock = 0;
        int maxStock = 10;

        //when
        when(artikelService.findeArtikelMitLagerbestandZwischen(minStock, maxStock)).thenReturn(artikelList);

        //act
        List<Artikel> result = artikelController.findeArtikelMitBestandBereich(minStock, maxStock);

        //assert
        assertEquals(1, result.size());
        verify(artikelService, times(1)).findeArtikelMitLagerbestandZwischen(minStock, maxStock);
    }
}
