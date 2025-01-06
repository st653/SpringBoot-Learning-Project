package com.example.demo.repository;

import com.example.demo.entity.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KundeRepository extends JpaRepository<Kunde, Long> {
}

