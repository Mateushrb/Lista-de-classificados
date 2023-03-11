package com.anuncios.classificados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anuncios.classificados.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
