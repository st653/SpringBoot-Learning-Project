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
}
