package com.gillianocampos.workshopmondo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gillianocampos.workshopmondo.domain.User;
import com.gillianocampos.workshopmondo.repository.UserRepository;

//essa classe sempre o projeto for aberto sera instanciando alguns usuarios implementando CommandLineRunner
@Configuration //para o Spring entender que é uma configuração
public class Instantiation implements CommandLineRunner{
	
	//injetar o repository para fazer operação com banco de dados
	@Autowired
	private UserRepository userRepository;
	

	//gerou automático o metodo
	@Override
	public void run(String... args) throws Exception {
		
		//para deletar a coleção la no banco mongodb , vai zerar a coleção
		userRepository.deleteAll();
		
		//vamos instanciar os Users, usei null no id para o banco gerar o id
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		//salva os dados no banco no mongodb criando uma lista
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		//ver no mongocompass e atualizar e vera os users instanciado. no postman tbm toda vez que rodar ele deleta os users que tiver e inserir esses 3 users
	}

}
