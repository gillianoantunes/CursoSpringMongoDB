package com.gillianocampos.workshopmondo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gillianocampos.workshopmondo.domain.Post;

@Repository //para indicar que é um repository e essa interface extend mongorepository <tipo da classe que vai gerenciar, tipo do id da classe>
//só com isso meu objeto UserRepository é capaz de fazer varias operações basicas com usuario como salvar,deletar, atualizar.tudo isso esta embutido nomongorepository
//agora criar um serviço para User chamada UserService

public interface PostRepository extends MongoRepository<Post, String> {
	
	//criar um metodo de busca  conforme palavra que eu quero pesquisar nos posts
	//metodo vai retornar uma lista de post com nome de findByTitle e na frente colocar a palavra Containing recebendo uma String text com argumento
	//querymethods so essa declaração abaixo faz com que o Spring Data monte a consulta
	List<Post> findByTitleContainingIgnoreCase(String text);
	//agora no PostService criar um método de busca
	//agora criar classe no subpacote Resources.util criar classe URL que faz a conversao da string que deseja pesquisar nos post para encode e tbm para nao diferenciar maiuscula minuscula
	//buscar bom dia no javascript encoda fica bom%20dia transforma espaços em branco %20 exemplo
	//essa classe entao pegara esse texto para decodificar
	//ultimo passo em PostResource implementar o endpoint e testar rodar no postman http://localhost:8080/posts/titlesearch?text=Bom%20dia
	//ele retorna os posts com Bom dia apenas maiusculo para ignorar maiuscula e minuscula colocar no repository incluir no metodo IgnoreCase
	//so isso ja basta para nao diferenciar maiuscula e minuscula
	
	//fazer a mesma consulta de string nos post de um outro modo pode ser nome quer quiser pois agora este metodo sera personalizado
  //@query e dentro do parenteses colocar a consulta json que esta nas especificações do mongodb
	//essa consulta eé do mongodb e pode ser usada em qualquer linguagem
	//title é o nome do campo que quero efetuar a busca 
	//?0 é o primeiro parametro que veio no metodo, no caso é text so tem 1
	//options o 'i' serve para ignorar maiusculas e minusculas
	@Query("{'title':{ $regex: ?0, $options: 'i'}}")
	List<Post> searchTitle(String text);
	//agora ir no serviço e trocar a chamada para esse searchtitle
}
