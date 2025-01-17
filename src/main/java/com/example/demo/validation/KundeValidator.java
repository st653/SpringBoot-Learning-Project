package com.example.demo.validation;

import com.example.demo.entity.Kunde;
import org.springframework.stereotype.Component;

@Component
public class KundeValidator {

    public static void validatePostInputParameter (Kunde kunde) {
        if (kunde.getName() == null || kunde.getName().isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht leer sein.");
        }
        System.out.println("Validation successful");
    }

    public static void validateUpdateedInputParameter (Kunde kunde) {
        if (kunde.getId() == null) {
            throw new IllegalArgumentException("Die Kunden-ID darf nicht null sein.");
        }
        if (kunde.getName() != null && kunde.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Der Name darf nicht leer sein, wenn er gesetzt ist.");
        }
        System.out.println("Validation successful");
    }
}
