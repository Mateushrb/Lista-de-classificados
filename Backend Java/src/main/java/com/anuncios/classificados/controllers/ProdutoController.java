package com.anuncios.classificados.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anuncios.classificados.entities.Produto;
import com.anuncios.classificados.services.ProdutoService;

@RestController
@CrossOrigin("http://localhost:3000")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@PutMapping(value = "/produtos/{id_produto}/addArquivo/{id_arquivo}")
	public ResponseEntity<Produto> addArquivo(@PathVariable Long id_produto, @PathVariable String id_arquivo) {
		Produto produto = produtoService.addArquivo(id_produto, id_arquivo);
		
		return ResponseEntity.ok().body(produto);
	}
	
	@PutMapping(value = "/produtos/{id_produto}/addCategoria/{id_categoria}")
	public ResponseEntity<Produto> addCategoria(@PathVariable Long id_produto, @PathVariable Long id_categoria) {
		Produto produto = produtoService.addCategoria(id_produto, id_categoria);
		
		return ResponseEntity.ok().body(produto);
	}
	
	@DeleteMapping(value = "/produtos/{id_produto}/removeCategoria")
	public ResponseEntity<Produto> removeCategoria(@PathVariable Long id_produto) {
		Produto produto = produtoService.removeCategoria(id_produto);
		
		return ResponseEntity.ok().body(produto);
	}
	
	@PutMapping(value = "/produtos/{id}")
	public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
		Produto produtoAtualizado = produtoService.update(id, produto);
		return ResponseEntity.ok().body(produtoAtualizado);
	}
	
	@DeleteMapping(value = "/produtos/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		produtoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/produtos")
	public ResponseEntity<Produto> save(@RequestBody Produto produto) {
		Produto produtoSaved = produtoService.save(produto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/{id}").buildAndExpand(produtoSaved.getId()).toUri();
		
		return ResponseEntity.created(uri).body(produtoSaved);
	}
	
	@GetMapping(value = "/produtos")
	public ResponseEntity<List<Produto>> findAll() {
		
		List<Produto> produtos = produtoService.findAll();
		return ResponseEntity.ok().body(produtos);
	}
	
	@GetMapping(value = "/produtos/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id){
		
		Produto c = produtoService.findById(id);
		return ResponseEntity.ok().body(c);
		
	}
}
