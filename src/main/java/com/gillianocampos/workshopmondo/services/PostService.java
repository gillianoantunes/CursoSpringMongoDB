package com.gillianocampos.workshopmondo.services;

import java.util.Date;
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
		//apaguei para chamar outro metodo que faz mesma coisa de uma maneira diferente return repo.findByTitleContainingIgnoreCase(text);
		 return repo.searchTitle(text);//chamando searchTitle agora e rodar e no postman fazer a consulta de palavras nos post http://localhost:8080/posts/titlesearch?text=bom%20dia
	}
	
	//metodo de consulta completa com data minima e maxima 
	public List<Post> pesquisaFull(String text, Date dataMin, Date dataMax){
		//acrescentar 1 dia em milissegundos na data maxima pq ele busca adata maxima até zero horas e não 24 horas
		dataMax = new Date(dataMax.getTime() + 24*60*60*1000);
		//retorno o repo.pequisacompleta do PostRepository
		return repo.pesquisacompleta(text, dataMin, dataMax);
	}
	//na classe url criar metodos tratar datas recebidas e depois em PostResource implementar o endpoint
	//no postamn fazer os testes exemplo http://localhost:8080/posts/pesquisacompleta?text=bom para ver quais tem palavra bom
	//exemplos de pesquisas com data maxima ate 30/03/2018 http://localhost:8080/posts/pesquisacompleta?text=bom&dataMax=2018-03-30
	//data exata do post http://localhost:8080/posts/pesquisacompleta?text=bom&dataMax=2018-03-22
	//data anterior 21 nao pode encontrar nada http://localhost:8080/posts/pesquisacompleta?text=bom&dataMax=2018-03-21
	//pesquisando texto "feliz" que esta no corpo http://localhost:8080/posts/pesquisacompleta?text=feliz&dataMax=2018-03-22
	//pesquisando o texto tenha que esta no comentario http://localhost:8080/posts/pesquisacompleta?text=tenha&dataMax=2018-03-22
	//pesquisa palavra aproveite que esta no segundo comentario http://localhost:8080/posts/pesquisacompleta?text=aproveite&dataMax=2018-03-22
	//testando a data minima agora http://localhost:8080/posts/pesquisacompleta?text=aproveite&dataMin=2018-03-20&dataMax=2018-03-22
	//outra pesquisa data minima 21 http://localhost:8080/posts/pesquisacompleta?text=aproveite&dataMin=2018-03-21&dataMax=2018-03-22 nao era pra encontra mas encontrou
	//este post foi instanciado na classe Instantiation dia 21/03/2018 so que no mongo esta mostrando dia 20 as 21 horas mas ele busca certo pq minha maquina esta no padrao gmt 3 horas
}
