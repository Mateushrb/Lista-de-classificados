package com.anuncios.classificados.entities;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class Arquivo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String nome;
	private String tipo;
	
	@JsonIgnore
	@OneToOne(mappedBy = "arquivo")
	private Produto produto;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public void removeProduto() {
		this.produto = null;
	}
	
	@Lob
	private byte[] dadosArquivo;
	
	public Arquivo() {}
	
	public Arquivo(String id, String nome, String tipo, byte[] dadosArquivo) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.dadosArquivo = dadosArquivo;
	}
	
	public String getId() {
		return id;
		
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public byte[] getDadosArquivo() {
		return dadosArquivo;
	}
	public void setDadosArquivo(byte[] dadosArquivo) {
		this.dadosArquivo = dadosArquivo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arquivo other = (Arquivo) obj;
		return Objects.equals(id, other.id);
	}
	
}
