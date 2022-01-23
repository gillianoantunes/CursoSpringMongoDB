package com.gillianocampos.workshopmondo.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gillianocampos.workshopmondo.services.exception.ObjectNotFoundException;

//essa classe controla as exceção na minha camada de resource
@ControllerAdvice // indica que essa classe é responsavel por tratar possiveis erros nas minhas
					// requisições
public class ResourceExceptionHandler {

	// metodo com nome objectNotFound dei esse nome pq vai tratar exceção de id não
	// encontrado
	// no parametro coloco o tipo da exceção que vou tratar no caso é tipo
	// ObjectNotFoundException uma exigencia do framework que é HttpServletRequest
	@ExceptionHandler(ObjectNotFoundException.class) // isso é padrao do framework tem que por isso para ele funcionar e
														// identificar que quando ocorrer essa exceção é para fazer esse
														// tratamento abaixo
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		// quando der essa exceção de id não encontrado vai receber de argumento
		// primeiro System.currentTimeMillis() pega o instante atual do sistema
		// segundo status.value() para converter para inteiro criar variavel pegando o
		// status do erro que é notfound 404
		// terceiro é error só escrever descrição do erro tipo "Não Encontrado"
		// quarto é a mensagem desse erro pegar da exceção ObjectNotFoundException e no
		// parametro do metodo que estamos e.getmessage()
		// quinto é o caminho que gerou a exceção vou usar esse objeto request do
		// paramentro para pegar o caminho request.getRequestURI()
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado",
				e.getMessage(), request.getRequestURI());
		// retornar o status para eu controlar manualmente qual codigo de status minha
		// requisição vai retornar e passando meu err como argumnto
		return ResponseEntity.status(status).body(err);
	}
	// ir no postman http://localhost:8080/users/7 e chamar usuario 7 que nao existe
	// este id7 e agora sim ele da erro 404 passando nossa mensagem acima
	// ele da esse erro:
	// "timestamp": 1642980226828,
	// "status": 404,
	// "error": "Não encontrado",
	// "message": "Objeto não encontrado",
	// "path": "/users/7"
}
