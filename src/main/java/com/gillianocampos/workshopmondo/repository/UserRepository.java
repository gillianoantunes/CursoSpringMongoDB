package com.gillianocampos.workshopmondo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gillianocampos.workshopmondo.domain.User;

@Repository //para indicar que é um repository e essa interface extend mongorepository <tipo da classe que vai gerenciar, tipo do id da classe>
//só com isso meu objeto UserRepository é capaz de fazer varias operações basicas com usuario como salvar,deletar, atualizar.tudo isso esta embutido nomongorepository
//agora criar um serviço para User chamada UserService

public interface UserRepository extends MongoRepository<User, String> {
	
}
