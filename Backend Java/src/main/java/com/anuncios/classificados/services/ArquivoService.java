package com.anuncios.classificados.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.anuncios.classificados.entities.Arquivo;
import com.anuncios.classificados.repositories.ArquivoRepository;

@Service
public class ArquivoService {
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	
	public Arquivo save(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Arquivo arquivo = new Arquivo(null, fileName, file.getContentType(), file.getBytes());
		
		return arquivoRepository.save(arquivo);
	}

	public void deleteById(String id) {
		arquivoRepository.deleteById(id);
	}
	
	public Arquivo get(String id) {
		return arquivoRepository.findById(id).get();
	}
}
