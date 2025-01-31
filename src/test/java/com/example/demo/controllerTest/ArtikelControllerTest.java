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
        closeable = MockitoAnnotations.openMocks(this);
        validArtikel = new Artikel("Laptop", 500.0, 5, 2.0);
        invalidArtikel = new Artikel("", -50.0, 0, 0.5);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    // Checks if a new, valid article is saved with http 200 ok
    @Test
    void neuerArtikel_ValidInput_ShouldReturnOk() {
        ResponseEntity<String> response = artikelController.neuerArtikel(validArtikel);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Neuer Artikel erstellt"));

        verify(artikelService, times(1)).erstelleArtikel(any(Artikel.class));
    }

    //Falsche Parameter in verify(): Das übergebene Artikel-Objekt im Test stimmt nicht mit dem im Controller verwendeten Objekt überein.
    //ID wird erst beim Speichern generiert?
}
