package com.procadproduto.dominio;

public class Produto {
	
	private int id;
	private String nome;
	private String descricao;
	private String fabricante;
	private int quantidade;
	private double preco;
	
	public Produto() {
	}

	public Produto(int id, String nome, String descricao, String fabricante, int quantidade, double preco) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.fabricante = fabricante;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	

}
