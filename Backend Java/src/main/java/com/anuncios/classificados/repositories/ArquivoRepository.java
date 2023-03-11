package com.anuncios.classificados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anuncios.classificados.entities.Arquivo;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, String> {

}
