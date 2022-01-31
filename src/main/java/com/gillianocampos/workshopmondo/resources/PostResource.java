package com.gillianocampos.workshopmondo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gillianocampos.workshopmondo.domain.Post;

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

}


