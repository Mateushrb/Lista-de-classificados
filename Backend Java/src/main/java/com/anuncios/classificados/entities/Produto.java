package com.anuncios.classificados.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double preco;
	private int quantidadeEstoque;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "arquivo_id")
	private Arquivo arquivo;
	
	private String idArquivo = "";
	
	public String getIdArquivo() {
		if (this.arquivo == null) {
			return "Sem arquivo";
		} else {
			this.idArquivo = arquivo.getId();
			return idArquivo;			
		}
	}
	
	public Produto(Long id, String nome, double preco, int quantidadeEstoque) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	public Produto() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public void removeCategoria() {
		this.categoria = null;
	}
	
	public Arquivo getArquivo() {
		return arquivo;
	}
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}
	public void removeArquivo() {
		this.arquivo = null;
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
}
