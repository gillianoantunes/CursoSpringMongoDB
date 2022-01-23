package com.gillianocampos.workshopmondo.resources;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gillianocampos.workshopmondo.domain.User;
import com.gillianocampos.workshopmondo.services.UserService;


@RestController // para falar que esta classe será um recurso Rest
@RequestMapping(value = "/users") // para falar qual o caminho do endpoint
public class UserResource {
	// o controlador Rest Resource acessa serviço e o serviço acessa o repository
	// igual no desenho
	// injetar o serviço para comunicar pois resource comunica com servico e la no
	// servico injetei um repository para comunicar com banco
	@Autowired // injeta automatico
	private UserService service;

	// metodo para retornar uma lista de usuarios
	@RequestMapping(method = RequestMethod.GET) // para dizer que este metodo vai ser o endpoint Rest nesse caminho e
												// entre parenteses o metodo http que vou usar. no caso get que obtem
												// informações no padrão Rest
	// outra for de fazer é so substitur a linha acima por @GetMapping apenas isso
	// public List<User> findAll(){ //substituir esse metodo pelo debaixo com
	// ResponseEntity com a lista dentro que ja torna uma respota no http mais certa
	// com cabeçalhos etc. no postman mostra direito esses cabeçalhos e dados
	// formatados
	public ResponseEntity<List<User>> findAll() {
		// esse objeto ResponseEntity com a lista dentro ele encapsula a lista ja com
		// cabeçalho para retornar uma resposta no http

		// variavel do tipo User
		// apagar essas 2 linhas pois não vou instaciaresses objetos abaixo na mao
		// User maria = new User("1", "Maria Silva", "maria@gmail.com");
		// User alex = new User("2", "Alex Green", "alex@gmail.com");

		// criar nova lista de usuario
		// apaguei pois nao vou criar lista vazia mais para adiconar
		// maria,alex,etcList<User> lista = new ArrayList<>();

		// adiconar a maria e o alex nesta lista
		// apaguei lista.addAll(Arrays.asList(maria,alex));

		// adicionei assim pois agora a lista recebe todos usuarios no banco de dados e
		// guardar essa lista
		List<User> lista = service.findAll();

		// metodo retona a minha lista mas troquei este retorno pq trocou este metodo
		// para ResponseEntity
		// return lista;
		// fica assim o retorno do metodo com ResponseEntity. o .ok é um metodo que ja
		// instancia o ResponseEntity jacom codigo de resposta http que a resposta
		// ocorreu com sucesso e na frente body para definir qual vai ser o corpo da
		// resposta no caso a lista
		return ResponseEntity.ok().body(lista);
		
		//em aplication.properties colocar os dados de conexão com o banco
		//spring.data.mongodb.uri=mongodb://localhost:27017/workshop_mongo sem espaços
		//tem que estar igual a porta o nome da base workshop_mongo
		// depois na linha de comando cmd digitar mongod para ativar
		//abrir o mongocompass proximo passo criar a base de dados no mongocompass as coleço~es (tabelas) User,etc ja criamos
		//inserir alguns dados de usuarios como maria,alex,etc em addData e insertDocument e clicar no menu
		//agora rodar no postman e testar o endpoint localhost:8080/users ja vem com id criado pelo banco de dados
		//criar uma classe de configuração chamada Instantiation sempre que iniciar o projeto sera carregado alguns usuarios no pacote config
	}
}
