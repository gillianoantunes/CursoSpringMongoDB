package com.gillianocampos.workshopmondo.dto;

import java.io.Serializable;

import com.gillianocampos.workshopmondo.domain.User;

//DTO tem como papel carregar dados das entidades de forma simples , podendo trazer apenas alguns dados deixando que senhas nao sendo carregadas
//otimiza o trafego, evita que dados como senhas etc fiquem expostos
//tem um construtor para instanciar a partir do objeto Entity no caso User
public class UserDTO implements Serializable{

	//serializable
	private static final long serialVersionUID = 1L;
	
	//fazer os mesmos atributos da classe User
	private String id;
	private String name;
	private String email;
	
	//construtor vazio
	public UserDTO() {
		
	}
	//construtor  recebendo como argumento um objeto do tipo User para ter forma automazida de instanciar UserDTO a partir do User
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();	
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
	
	//em UserResource tem que refatorar o metodo findAll
	
}
