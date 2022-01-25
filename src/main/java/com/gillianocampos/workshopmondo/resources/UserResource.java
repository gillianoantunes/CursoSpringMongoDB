package com.gillianocampos.workshopmondo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gillianocampos.workshopmondo.domain.User;
import com.gillianocampos.workshopmondo.dto.UserDTO;
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
	// apaguei tbm e agora pus a lista DTO par retornar public
	// ResponseEntity<List<User>> findAll() {
	public ResponseEntity<List<UserDTO>> findAll() {
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

		// para dto eu tenho que converter essa lista de User para UserDTO usando lambda
		// lista.stream para transformar numa stream que é uma coleção compativel com
		// expressoes lambda a partir do java 8
		// map pega cada objeto x da minha lista original x pode ser nome que quiser e
		// para cada objeto x eu retorno um new UserDTO passando o x como argumento para
		// chamar o construtor que recebe um User no paramentro na classe UserDTO
		// collect(Collectors.toList() volta a lista que esta stream para lista
		// novamente
		// entao resumindo passa a lista para stream para usar lambda depois retorna
		// para lista novamente assim que acabar de usar a lambda
		List<UserDTO> listaDTO = lista.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		// metodo retona a minha lista mas troquei este retorno pq trocou este metodo
		// para ResponseEntity
		// return lista;
		// fica assim o retorno do metodo com ResponseEntity. o .ok é um metodo que ja
		// instancia o ResponseEntity jacom codigo de resposta http que a resposta
		// ocorreu com sucesso e na frente body para definir qual vai ser o corpo da
		// resposta no caso a lista
		// agora como mudei vou retornar a listaDTO
		return ResponseEntity.ok().body(listaDTO);// depois vai no postman e ver se aparece os 3 Users

		// em aplication.properties colocar os dados de conexão com o banco
		// spring.data.mongodb.uri=mongodb://localhost:27017/workshop_mongo sem espaços
		// tem que estar igual a porta o nome da base workshop_mongo
		// depois na linha de comando cmd digitar mongod para ativar
		// abrir o mongocompass proximo passo criar a base de dados no mongocompass as
		// coleço~es (tabelas) User,etc ja criamos
		// inserir alguns dados de usuarios como maria,alex,etc em addData e
		// insertDocument e clicar no menu
		// agora rodar no postman e testar o endpoint localhost:8080/users ja vem com id
		// criado pelo banco de dados
		// criar uma classe de configuração chamada Instantiation sempre que iniciar o
		// projeto sera carregado alguns usuarios no pacote config
	}

	// metodo para retornar um usuario por Id agora quase igual a primeiro findAll
	// value="/{id}" indica que este metodo tera caminbho /users/id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// não vai rtornar lsta agora apenas um UserDTO e vai receber como arguemnto uma
	// string id
	// @PathVariable para eu falar que esse id tem que casar com id recebido na url
	// tem que por essa anotation
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		// criar um objeto usuario recebendo o service acima .findById com paraemntro id
		// que recebi no metodo
		User obj = service.findById(id);
		// retorna o obj convertido para UserDTO
		// new UserDTO(obj) para converter obj para UserDTO fazer new UserDTO passando o
		// objeto como argumento
		return ResponseEntity.ok().body(new UserDTO(obj));
		// agora testa no postman chamando todos usuarios http://localhost:8080/users
		// e tbm passando o id da maria por exemplo
		// http://localhost:8080/users/61edda61e1b8fa5bd4d044ca
		// agora passndo um id que nao existe http://localhost:8080/users/4 da um erro
		// 500 não podemos deixar tem que retornar 404 não encontrado
		// criar as classes StandardError e ResourceExceptionHandler no subpacote
		// .exception

	}

	// metodo inserir post
	@RequestMapping(method = RequestMethod.POST) // ou @PostMapping
	// a inserção vai retornar void ou seja um objeto vazio e como argumento recebe
	// um UserDto
	// @RequestBody para que esse endpoint aceite este objeto DTO
	public ResponseEntity<Void> inserir(@RequestBody UserDTO objDto) {
		// converter dto para user

		User obj = service.fromDTO(objDto);
		obj = service.inserir(obj);
		// retornar uma resposta vazio pegando o endereço localização do novo objeto que
		// inseri com codigo 201 created
		// esse uri traz o caminho do objeto inserido com id dele no postman quando der
		// o post na aba headers
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		// no postman execuat metodo post passando json abaixo para inserir na aba body
		// e mudar para json
		// depois pesquisar no get se o os 4 objetos sao mostrado
		// {
		// "name":"Jose",
		// "email":"Jose@gmail.com"
		// }
	}

	// delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		// não retorna nada apenas codigo 204 noContent
		return ResponseEntity.noContent().build();

		// rodar e tentar deletar no postman chamando usando id que nao existe delete
		// http://localhost:8080/users/78785
		// dara codigo 404 nao encontrado, correto entao
		// agora da um get pra pegar um id e depois da um delete usando um id existente
		// deu codigo 204 nocontent entao certo e agora dar um get pra ver se o usuario
		// foi deletado
	}

	// metodo update put colocar id pq para alterar precisa especificar o id
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT) // ou @PostMapping
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto,@PathVariable String id) {
		//instanciar um obj pelo objDto que vira na requisição
		User obj = service.fromDTO(objDto);
		//antes de chamar o update eu vou fazer obj.setId para ter o ID na requisição que veio no parametro
		obj.setId(id);
		//chamar o update agora
		obj = service.update(obj);
		//colocar uma resposta de noContent igual no delete
		// não retorna nada apenas codigo 204 noContent
		return ResponseEntity.noContent().build();
		//fazer um put no postman com algum codigo e passar no corpo a alteração do nome e email , dara resposta noContent 204 tudo certo e depois da um get pra ver se alterou
	}
}
