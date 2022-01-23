package com.gillianocampos.workshopmondo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gillianocampos.workshopmondo.domain.User;

@RestController //para falar que esta classe será um recurso Rest
@RequestMapping(value ="/users") //para falar qual o caminho do endpoint
public class UserResource {
	
	
	//metodo para retornar uma lista de usuarios
	@RequestMapping(method=RequestMethod.GET) //para dizer que este metodo vai ser o endpoint Rest nesse caminho e entre parenteses o metodo http que vou usar. no caso get que obtem informações no padrão Rest
	//outra for de fazer é so substitur a linha acima por @GetMapping apenas isso
	//public List<User> findAll(){ //substituir esse metodo pelo debaixo com ResponseEntity com a lista dentro que ja torna uma respota no http mais certa com cabeçalhos etc. no postman mostra direito esses cabeçalhos e dados formatados
	public ResponseEntity<List<User>> findAll(){
		// esse objeto ResponseEntity com a lista dentro ele encapsula a lista ja com cabeçalho para retornar uma resposta no http
		
		//variavel do tipo User
		User maria = new User("1", "Maria Silva", "maria@gmail.com");
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		
		//criar nova lista de usuario
		List<User> lista = new ArrayList<>();
		
		//adiconar a maria e o alex nesta lista
		lista.addAll(Arrays.asList(maria,alex));
		
		//metodo retona a minha lista mas troquei este retorno pq trocou este metodo para ResponseEntity
		//return lista;
		//fica assim o retorno do metodo com ResponseEntity. o .ok é um metodo que ja instancia o ResponseEntity jacom codigo de resposta http que a resposta ocorreu com sucesso e na frente body para definir qual vai ser o corpo da resposta no caso a lista
		return ResponseEntity.ok().body(lista);
	}
}
