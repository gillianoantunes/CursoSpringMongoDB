package com.gillianocampos.workshopmondo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gillianocampos.workshopmondo.domain.Post;
import com.gillianocampos.workshopmondo.repository.PostRepository;
import com.gillianocampos.workshopmondo.services.exception.ObjectNotFoundException;

//copiei e colei UserResource e troquei para PostResource
//serviço responsavel para trabalhar com posts
@Service // para eu falar pro Spring essa classe vai ser um serviço que pode ser
			// injetavel em outras classes
public class PostService {
//o meu serviço tem que conversar com repository vamos instanciar um serviço

	@Autowired // para instanciar automaticamente um objeto UserRepository no servico, não
				// precisa fazer mais nada injeção de depencia automatica do spring
	private PostRepository repo;


	//metodo igual metodo que busca user por id em PostS5ervice
	// metodo que vai buscar por id retornando um post recebendo uma string id
	// como argumento
	public Post findById(String id) {
		// ja tem pronto no repositorio vc passa o id ele retorna o usuario e se nao
		// existir o id ele retorna nulo
		Optional<Post> obj = repo.findById(id);
		// se for nulo não exite o usuario lança exceção personalizada que fiz na clase
		// ObjectNotFoundException
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	//metodo que declaramos no PostRepository para retornar uma lista de post que contenha a string
	//pode dar o nome que quiser aqui no metodo..no caso pus mais ou menos parecido com o do PostRepository
	//vai retornar o repo.findByTitleContaining
	public List<Post> findByTitle(String text){
		return repo.findByTitleContainingIgnoreCase(text);
	}
	
}
