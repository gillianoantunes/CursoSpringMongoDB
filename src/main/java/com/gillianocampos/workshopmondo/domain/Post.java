package com.gillianocampos.workshopmondo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gillianocampos.workshopmondo.dto.AuthorDTO;
import com.gillianocampos.workshopmondo.dto.CommentDTO;

@Document
public class Post {

	//atributos
	@Id
	private String id;
	private Date date;
	private String title;
	private String body;
	
	//atributo da outra tabela tipo User
	//private User author;
	//mudei para dto que pega apenas id e name do User fazendo com que não exponha a classe User outros dados
	private AuthorDTO author;
	
	//Lista de CommentDTO incluir este atributo pois fizemos o CommentDTO
	private List<CommentDTO> comments = new ArrayList<>();
	public Post() {
		
	}

	public Post(String id, Date date, String title, String body, AuthorDTO author) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.body = body;
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
	//get e set da lista de CommentDTO comments
	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	//depois fazer o PostRepository só copiar e colar o UserRepository e trocar nome para post
	//deppois na classe Instantiation criar injeçao do postrepository instanciar os post e salvar
	
	
}
