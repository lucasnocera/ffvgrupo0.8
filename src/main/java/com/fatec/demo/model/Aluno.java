package com.fatec.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.NaturalId;

@Entity(name = "Aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Column(nullable = false, length = 100)
	@NotEmpty(message = "O nome deve ser preenchido") // o atributo nao pode ser nulo e o tamanho > zero
	private String nome;
	
	@Column(nullable = false, length = 13)
	@NotEmpty(message = "O RA deve ser preenchido")
	private String ra;
	
	@Column(nullable = false, length = 100)
	@NotEmpty(message = "O curso deve ser preenchido")
	private String curso;
	
	@Column(nullable = false, length = 8)
	@NotEmpty(message = "CEP deve ter 8 caracteres:")
	private String cep;
	
	public Aluno() {
	}

	public Aluno(String i, String t, String c) {
		this.nome = i;
		this.ra = t;
		this.curso = c;
	}

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

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}
	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}


}
