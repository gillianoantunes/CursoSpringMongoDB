package com.gillianocampos.workshopmondo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gillianocampos.workshopmondo.domain.User;
import com.gillianocampos.workshopmondo.repository.UserRepository;

//serviço responsavel para trabalhar com usuarios
@Service // para eu falar pro Spring essa classe vai ser um serviço que pode ser
			// injetavel em outras classes
public class UserService {
//o meu serviço tem que conversar com repository vamos instanciar um serviço

	@Autowired // para instanciar automaticamente um objeto UserRepository no servico, não
				// precisa fazer mais nada injeção de depencia automatica do spring
	private UserRepository repo;

	// metodo resposanvel por retornar todos usuãrios do meu banco
	public List<User> findAll() {
		//chamar o repository com as operações do banco ele tem o findAll ja pronto que ja vai no banco e retorna todos os objetos deste tipo no caso User 
		//entao o serviço esta comunicando com repository e o resource chama o serviço
		//na classe user colocar Anotation @Document para indicar que essa classe corresponde a uma coleção ou tabela la no mongoDB
		return repo.findAll();
	}
}
