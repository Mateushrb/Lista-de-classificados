package com.anuncios.classificados.start;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.anuncios.classificados.entities.Arquivo;
import com.anuncios.classificados.entities.Categoria;
import com.anuncios.classificados.entities.Produto;
import com.anuncios.classificados.repositories.ArquivoRepository;
import com.anuncios.classificados.repositories.CategoriaRepository;
import com.anuncios.classificados.repositories.ProdutoRepository;


@Configuration
public class setup implements CommandLineRunner {
	
	// dependency injection
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Mobilidade");
		Categoria cat2 = new Categoria(null, "Descanso");
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		File bicicleta = new File("./src/main/java/com/anuncios/classificados/start/imgClassificados/bicicleta.png");
		InputStream bicicletaBytes = new FileInputStream(bicicleta.getCanonicalPath());
		Arquivo bicicletaGuardar = new Arquivo(null, bicicleta.getName(), Files.probeContentType(bicicleta.toPath()), bicicletaBytes.readAllBytes());
		
		File carro = new File("./src/main/java/com/anuncios/classificados/start/imgClassificados/carro.jpg");
		InputStream carroBytes = new FileInputStream(carro.getCanonicalPath());
		Arquivo carroGuardar = new Arquivo(null, carro.getName(), Files.probeContentType(carro.toPath()), carroBytes.readAllBytes());
		
		File helicoptero = new File("./src/main/java/com/anuncios/classificados/start/imgClassificados/helicóptero.jpg");
		InputStream helicopteroBytes = new FileInputStream(helicoptero.getCanonicalPath());
		Arquivo helicopteroGuardar = new Arquivo(null, helicoptero.getName(), Files.probeContentType(helicoptero.toPath()), helicopteroBytes.readAllBytes());
		
		File cadeira = new File("./src/main/java/com/anuncios/classificados/start/imgClassificados/cadeira.jpg");
		InputStream cadeiraBytes = new FileInputStream(cadeira.getCanonicalPath());
		Arquivo cadeiraGuardar = new Arquivo(null, cadeira.getName(), Files.probeContentType(cadeira.toPath()), cadeiraBytes.readAllBytes());

		File sofa = new File("./src/main/java/com/anuncios/classificados/start/imgClassificados/sofá.jpg");
		InputStream sofaBytes = new FileInputStream(sofa.getCanonicalPath());
		Arquivo sofaGuardar = new Arquivo(null, sofa.getName(), Files.probeContentType(sofa.toPath()), sofaBytes.readAllBytes());

		File cama = new File("./src/main/java/com/anuncios/classificados/start/imgClassificados/cama.jpg");
		InputStream camaBytes = new FileInputStream(cama.getCanonicalPath());
		Arquivo camaGuardar = new Arquivo(null, cama.getName(), Files.probeContentType(cama.toPath()), camaBytes.readAllBytes());

		arquivoRepository.saveAll(Arrays.asList(bicicletaGuardar, carroGuardar, helicopteroGuardar, cadeiraGuardar, sofaGuardar, camaGuardar));
		
		
		Produto p1 = new Produto(null, "Bicicleta", 3299.00, 20);
		p1.setCategoria(cat1);
		p1.setArquivo(bicicletaGuardar);
		
		Produto p2 = new Produto(null, "Carro", 91390.00, 8);
		p2.setCategoria(cat1);
		p2.setArquivo(carroGuardar);
		
		Produto p3 = new Produto(null, "Helicóptero", 2600000.00, 2);
		p3.setCategoria(cat1);
		p3.setArquivo(helicopteroGuardar);
		
		Produto p4 = new Produto(null, "Cadeira", 187.89, 15);
		p4.setCategoria(cat2);
		p4.setArquivo(cadeiraGuardar);
		
		Produto p5 = new Produto(null, "Sofá", 1188.99, 4);
		p5.setCategoria(cat2);
		p5.setArquivo(sofaGuardar);
		
		Produto p6 = new Produto(null, "Cama", 875.91, 10);
		p6.setCategoria(cat2);
		p6.setArquivo(camaGuardar);
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
		cat1.setProdutos(Arrays.asList(p1, p2, p3));
		cat2.setProdutos(Arrays.asList(p4, p5, p6));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
//		bicicletaGuardar.setProduto(p1);
//		carroGuardar.setProduto(p2);
//		helicopteroGuardar.setProduto(p3);
//		cadeiraGuardar.setProduto(p4);
//		sofaGuardar.setProduto(p5);
//		camaGuardar.setProduto(p6);
//		arquivoRepository.saveAll(Arrays.asList(bicicletaGuardar, carroGuardar, helicopteroGuardar, cadeiraGuardar, sofaGuardar, camaGuardar));
		
	}
}
