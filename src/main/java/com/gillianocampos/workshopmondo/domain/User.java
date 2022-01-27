package com.gillianocampos.workshopmondo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//colocar Anotation @Document para indicar que essa classe corresponde a uma coleção ou tabela la no mongoDB
//(collection="user") é opcional se vc nao colocar  automaticamente o springdata mapeia a coleção com o mesmo nome da classe com letra minuscula
@Document(collection="user")
public class User implements Serializable {

	//serializable
	private static final long serialVersionUID = 1L;
	
	//atributos
	//colocar @Id em cima do atributo que for a chave
	@Id
	private String id;
	private String name;
	private String email;
	
	//esse atributo dentro de usuario User terá posts e instanciar quando é lista
	//quando é coleção temos que iniciar instanciar
	//criar get e set
	  @DBRef(lazy = true) //@DBRef no Spring Data para voce falar que um atributo esta referenciando outra coleção ou tabela do banco MongoDB no caso classe Post, estou aninhando 
	//(lazy = true) como estamos referenciando uma coleção nao quero que quando recuperar um usuario sempre retornar a lista de post para evitar muitos dados trafego nesse caso 
	//(lazy = true) garante que os post so serao carregados se eu acessa-los
	  //agora na classe instantiation refatorar incluindo as associções dos posts esse atributo que acabei de fazer
	private List<Post> posts = new ArrayList<>();
	
	//construtores vazio e com argumentos
	public User() {
		
	}

	public User(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//get e set da lista de posts
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	//hashcode e equals
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
