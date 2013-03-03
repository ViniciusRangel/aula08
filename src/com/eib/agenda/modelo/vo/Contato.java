package com.eib.agenda.modelo.vo;

public class Contato {
	private int id;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;

	public Contato() {
	}

	public Contato(int id, String nome, String endereco, String telefone,
			String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
	}

	public Contato(String nome, String endereco, String telefone, String email) {
		this(0, nome, endereco, telefone, email);
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		
		return this.id + ":" + this.nome;
	}
}
