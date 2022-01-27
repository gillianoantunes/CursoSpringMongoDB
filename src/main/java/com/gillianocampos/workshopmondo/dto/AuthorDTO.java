package com.gillianocampos.workshopmondo.dto;

import java.io.Serializable;

import com.gillianocampos.workshopmondo.domain.User;

//classe que ira armazenar junto com post apenas o ID e o nome do author e não todos os dados dele em User
//vai projetar apenas os dados que eu queira
public class AuthorDTO implements Serializable{
	
	//serializable
	private static final long serialVersionUID = 1L;
	
	//atributos que eu quero da classe User quando estiver aninhado com algum post
	private String id;
	private String name;
	
	//construtor vazio
	public AuthorDTO() {
		
	}
	
	//construtor que vai pegar o User com argumento para pegar apenas id e string
	public AuthorDTO (User obj) {
		//os atributos desta classe recebe os dados do obj User que veio do parametro para eu usar apenas o dto
		id = obj.getId();
		name = obj.getName();
	}
	
	//get e set
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//dto não precisa de hashcode e equals
	//depois disso eu tenho que mudar o Post que tem atributo do tipo User e agora eu vou usar apenas o DTO para não expor a classe User
	//trocar tbm no construtor para AuthorDTO e no get e set tbm na classe Post
	// e agora da erro na classe Instantiation refatorar tbm la trocando User por AuthorDTO na instanciação fazendo um new AuthorDTO(User) recebendo esse usuario como argumento
}
