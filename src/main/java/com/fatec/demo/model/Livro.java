package com.fatec.demo.model;

import javax.persistence.*;

import javax.validation.constraints.*;

import org.hibernate.annotations.NaturalId;

@Entity(name = "Livro")
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NaturalId
	@Column(nullable = false, length = 4)
	@NotEmpty(message = "O isbn deve ser preenchido") // o atributo nao pode ser nulo e o tamanho > zero
	private String isbn;
	@Column(nullable = false, length = 100)
	@NotEmpty(message = "O titulo deve ser preenchido")
	private String titulo;
	@Column(nullable = false)
	@NotNull(message = "Autor invalido")
	@Size(min = 1, max = 50, message = "Autor deve ter entre 1 e 50 caracteres")
	private String autor;

	public Livro() {
	}

	public Livro(String i, String t, String a) {
		this.isbn = i;
		this.titulo = t;
		this.autor = a;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
}
