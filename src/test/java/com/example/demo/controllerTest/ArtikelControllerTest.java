package com.example.demo.controllerTest;

import com.example.demo.controller.ArtikelController;
import com.example.demo.entity.Artikel;
import com.example.demo.service.ArtikelService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArtikelControllerTest {

    @Mock
    private ArtikelService artikelService;

    @InjectMocks
    private ArtikelController artikelController;

    private Artikel validArtikel;
    private Artikel invalidArtikel;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        validArtikel = new Artikel("Laptop", 500.0, 5, 2.0);
        invalidArtikel = new Artikel("", -50.0, 0, 0.5);
    }

    // Checks if a new, valid article is saved with http 200 ok
    @Test
    void neuerArtikel_ValidInput_ShouldReturnOk() {
        //given

        //when

        //act
        ResponseEntity<String> response = artikelController.neuerArtikel(validArtikel);

        //assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Neuer Artikel erstellt"));

        verify(artikelService, times(1)).erstelleArtikel(any(Artikel.class));
    }

    // Checks if a new, invalid article is not saved with http 400 bad request
    @Test
    void neuerArtikel_InvalidInput_ShouldReturnBadRequest() {
        //given

        //when

        //act
        ResponseEntity<String> response = artikelController.neuerArtikel(invalidArtikel);

        //assert
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Validation failed"));

        verify(artikelService, never()).erstelleArtikel(any(Artikel.class));
    }
}
