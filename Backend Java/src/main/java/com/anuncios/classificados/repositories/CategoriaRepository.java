package com.anuncios.classificados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anuncios.classificados.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
