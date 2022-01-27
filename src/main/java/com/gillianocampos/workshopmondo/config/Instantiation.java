package com.gillianocampos.workshopmondo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gillianocampos.workshopmondo.domain.Post;
import com.gillianocampos.workshopmondo.domain.User;
import com.gillianocampos.workshopmondo.dto.AuthorDTO;
import com.gillianocampos.workshopmondo.repository.PostRepository;
import com.gillianocampos.workshopmondo.repository.UserRepository;

//essa classe sempre o projeto for aberto sera instanciando alguns usuarios implementando CommandLineRunner
@Configuration // para o Spring entender que é uma configuração
public class Instantiation implements CommandLineRunner {

	// injetar o repository do User para fazer operação com banco de dados
	@Autowired
	private UserRepository userRepository;

	// injetar o repository do Post para fazer operação com banco de dados
	@Autowired
	private PostRepository postRepository;

	// gerou automático o metodo
	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		// para deletar a coleção la no banco mongodb , vai zerar a coleção
		userRepository.deleteAll();
		postRepository.deleteAll();

		// vamos instanciar os Users, usei null no id para o banco gerar o id
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		// salva os dados do User no banco no mongodb criando uma lista
		//salvar antes de instanciar os posts para que o id nao fique nulo, pq salvando antes depois que instanciar os posts ele ja pega os ids
		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		// instanciar post apaguei pra trocar pra dto ficara new AuthorDTO(User) recebendo User como argumento para pegar apenas id e name chamdno o construtor
		//Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para SãoPaulo", maria);
		//Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje", maria);
		//ficara assim mudando User para DTO chama o construtor
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para SãoPaulo", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(maria));
		//mas agora precisamos salvar antes os usuario antes de instanciar oos posts para depois fazer a copia pdo User para DTO, se noa salvar antes o id do user os post ficara null
		
		
		// salva os dados do Post no banco no mongodb criando uma lista
		postRepository.saveAll(Arrays.asList(post1, post2));

		// ver no mongocompass e atualizar e vera os users instanciado. no postman tbm
		// toda vez que rodar ele deleta os users e posts que tiver e inserir esses 3 users e inseri os post atrelados com o author
	  //verificar no mongodbCompass as tabelas craiadas e os objetos user e os posts atrelado com o usuario do post uma copia do user que fez o post
	
	//o atributo post que coloquei na classe User para referenciar os post da classe post
	  //o objeto User maria estamos salvando o post1 e post2 qu e é dela
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		//salvar no objeto maria  e agora as referencias de post da maria serao salvas no objeto maria
		userRepository.save(maria);
	
	}
	

}
