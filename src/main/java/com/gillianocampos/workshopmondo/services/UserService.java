package com.gillianocampos.workshopmondo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gillianocampos.workshopmondo.domain.User;
import com.gillianocampos.workshopmondo.dto.UserDTO;
import com.gillianocampos.workshopmondo.repository.UserRepository;
import com.gillianocampos.workshopmondo.services.exception.ObjectNotFoundException;

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
		// chamar o repository com as operações do banco ele tem o findAll ja pronto que
		// ja vai no banco e retorna todos os objetos deste tipo no caso User
		// entao o serviço esta comunicando com repository e o resource chama o serviço
		// na classe user colocar Anotation @Document para indicar que essa classe
		// corresponde a uma coleção ou tabela la no mongoDB
		return repo.findAll();
	}

	// metodo que vai buscar por id retornando um usuario recebendo uma string id
	// como argumento
	public User findById(String id) {
		// ja tem pronto no repositorio vc passa o id ele retorna o usuario e se nao
		// existir o id ele retorna nulo
		Optional<User> obj = repo.findById(id);
		// se for nulo não exite o usuario lança exceção personalizada que fiz na clase
		// ObjectNotFoundException
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	// agora em UserResource retornar o dto no metodo findById
	// abaixo incluir metodo para inserir post que vai receber um User para inserir
	public User inserir(User obj) {
		// o repositorio ja tem operação insert nele
		return repo.insert(obj);
	}
	
	//metodo delete recebendo um id como argumentp	
	public void delete(String id) {
		//para tratar exceçao caso id a ser deletado não exista eu vou aproveitar o metodo findById acima para ele fazer uma busca se não encontrar o objeto ja lança a exceção
		findById(id);
		//função que ja existe no repositorio deleteById
		repo.deleteById(id);
		//agora implementar no UserResource		
		
	}
	// depois de inserir implementar um FromDTO e como aqui no User Service eu ja tenho repositorio de acesso a dados eu vou fazer aqui ao inves de fazer na classe UserDTO
	//vai receber um objDTO e retornar um new User recebendo o dados do DTO como paramentro
	//metodo converte UserDto para User
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(),objDto.getEmail());
	}
	//em UserResource implementar o método inserir
	
	
}
