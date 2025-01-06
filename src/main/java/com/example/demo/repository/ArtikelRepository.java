package com.example.demo.repository;

import com.example.demo.entity.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
}
