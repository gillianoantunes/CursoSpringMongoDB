package com.gillianocampos.workshopmondo.dto;

import java.io.Serializable;
import java.util.Date;

//não precisa criar a classe entidade Comment e sim apenas CommentDTO pois são dados simples e não vou fazer busca por comentarios
//os comentarios terao texto, data e o author do mesmo tipo do Post
public class CommentDTO implements Serializable {
	
	//serializable
	private static final long serialVersionUID = 1L;
	
	//atributos
	private String text;
	private Date date;
	private AuthorDTO author;
	
	//construtor vazio e com argumentos
	public CommentDTO() {
		
	}

	public CommentDTO(String text, Date date, AuthorDTO author) {
		super();
		this.text = text;
		this.date = date;
		this.author = author;
	}

	//get e set
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
	//na classe Post incluir uma lista de CommentDTO
	//criar get e set deste atributo na classe Post
	//depois na classe instantiation que é classe que é executada quando roda o sistema e incluir alguns comentarios no post
}
