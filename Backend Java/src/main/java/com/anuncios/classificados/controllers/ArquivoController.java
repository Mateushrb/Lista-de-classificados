package com.anuncios.classificados.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.anuncios.classificados.entities.Arquivo;
import com.anuncios.classificados.services.ArquivoService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin("http://localhost:3000")
public class ArquivoController {
	
	@Autowired
	private ArquivoService arquivoService;
	
	@DeleteMapping("/arquivo/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		arquivoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/arquivo")
	public ResponseEntity<Arquivo> save(@RequestParam("image") MultipartFile multipartFile) throws IOException {
		Arquivo arquivo = arquivoService.save(multipartFile);
		
		return ResponseEntity.ok().body(arquivo);
	}
	
	@RequestMapping(value = "/arquivo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void showImage(@PathVariable String id, HttpServletResponse response) throws IOException {
		Arquivo arquivo = arquivoService.get(id);
		
		response.setContentType(arquivo.getTipo());
		response.getOutputStream().write(arquivo.getDadosArquivo());
		response.getOutputStream().close();
	}
}







