package com.gillianocampos.workshopmondo.resources;

import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gillianocampos.workshopmondo.domain.Post;
import com.gillianocampos.workshopmondo.resources.util.URL;
import com.gillianocampos.workshopmondo.services.PostService;

@RestController // para falar que esta classe será um recurso Rest
@RequestMapping(value = "/posts") // para falar qual o caminho do endpoint
public class PostResource {

	@Autowired // injeta automatico
	private PostService service;

	//endpoint para retornar um post atraves de um id do postman no parametro
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		//roda , pega um id de um post no mongo compass e vai no postman e coloca/posts/idcopiado http://localhost:8080/posts/61f725feb2184e16ae1ee99f
	}

	
	//endpoint para buscar posts que contenha o text do parametro
	//get pois é busca no padrao rest usamos get
	//caminho /titlesearch , nome findByTitle
	//(@RequestParam(value = "text", defaultValue="") String text) se text nao for informado como default ponho string vazia
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue="") String text) {
		//a propria variavel text chama o metodo na classe URL chamado decodeParam que encoda o texto
		text = URL.decodeParam(text);
		//declara uma lista de post recebendo o service.findByTitle que tem no servico
		List<Post> lista = service.findByTitle(text);
		//retorna resposta cujo corpo sera a lista
		return ResponseEntity.ok().body(lista);
	}
}


