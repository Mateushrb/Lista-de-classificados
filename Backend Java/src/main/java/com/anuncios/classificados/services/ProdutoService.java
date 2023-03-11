package com.anuncios.classificados.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anuncios.classificados.entities.Arquivo;
import com.anuncios.classificados.entities.Categoria;
import com.anuncios.classificados.entities.Produto;
import com.anuncios.classificados.repositories.ArquivoRepository;
import com.anuncios.classificados.repositories.CategoriaRepository;
import com.anuncios.classificados.repositories.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ArquivoRepository arquivoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public Produto addArquivo(Long idProduto, String idArquivo) {
		Produto produto = produtoRepository.findById(idProduto).get();
		Arquivo arquivo = arquivoRepository.findById(idArquivo).get();
		
		produto.setArquivo(arquivo);
		produtoRepository.save(produto);
		return produto;
	}
	
	public Produto addCategoria(Long idProduto, Long idCategoria) {
		Produto produto = produtoRepository.findById(idProduto).get();
		
		Categoria categoria = categoriaRepository.findById(idCategoria).get();
		
		produto.setCategoria(categoria);
		produtoRepository.save(produto);

		return produto;
	}
	
	public Produto removeCategoria(Long idProduto) {
		Produto produto = produtoRepository.findById(idProduto).get();
		
		produto.removeCategoria();
		produtoRepository.save(produto);
		
		return produto;
	}
	
	public Produto update(Long id, Produto produto) {
		Produto produtoEntity = produtoRepository.getReferenceById(id); // lazy
		produtoEntity.setNome(produto.getNome());
		produtoEntity.setPreco(produto.getPreco());
		produtoEntity.setQuantidadeEstoque(produto.getQuantidadeEstoque());
		produtoEntity.setCategoria(produto.getCategoria());
		
		return produtoRepository.save(produtoEntity);
	}
	
	public void deleteById(Long id) {
		Produto p = produtoRepository.findById(id).get();
		produtoRepository.deleteById(id);
		arquivoRepository.deleteById(p.getArquivo().getId());
	}
	
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	public Produto findById(Long id) {
		try {
			return produtoRepository.findById(id).get();
		} catch(NoSuchElementException e) {
			throw new EntityNotFoundException("EntityNotFoundException Produto id: " + id);
		}
	}
}
