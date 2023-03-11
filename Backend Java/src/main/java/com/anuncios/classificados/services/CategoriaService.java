package com.anuncios.classificados.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anuncios.classificados.entities.Categoria;
import com.anuncios.classificados.entities.Produto;
import com.anuncios.classificados.repositories.ArquivoRepository;
import com.anuncios.classificados.repositories.CategoriaRepository;
import com.anuncios.classificados.repositories.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired 
	private ArquivoRepository arquivoRepository;
	
	public Categoria addProduto(Long idCategoria, Long idProduto) {
		Categoria categoria = categoriaRepository.findById(idCategoria).get();
		
		Produto produto = produtoRepository.findById(idProduto).get();
		
		categoria.addProduto(produto);
		categoriaRepository.save(categoria);

		return categoria;
	}
	
	public Categoria removeProduto(Long idCategoria, Long idProduto) {
		Categoria categoria = categoriaRepository.findById(idCategoria).get();
		
		Produto produto = produtoRepository.findById(idProduto).get();
		
		categoria.removeProduto(produto);
		categoriaRepository.save(categoria);
		
		return categoria;
	}
	
	public Categoria update(Long id, Categoria categoria) {
		Categoria categoriaEntity = categoriaRepository.getReferenceById(id); // lazy
		categoriaEntity.setNome(categoria.getNome());
		
		return categoriaRepository.save(categoriaEntity);
	}
	
	public void deleteById(Long id) {
		Categoria categoria = findById(id);
		List<Produto> produtos = categoria.getProdutos();
		for (Produto i : produtos) {
			produtoRepository.deleteById(i.getId());
			arquivoRepository.deleteById(i.getArquivo().getId());
		}
		
		categoriaRepository.deleteById(id);
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Categoria findById(Long id) {
		try {
			return categoriaRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new EntityNotFoundException("EntityNotFoundException Categoria id: " + id);
		}
	}
}
