package com.gillianocampos.workshopmondo.services.exception;

//exceção que vamos criar para quando fazer uma pesquisa por id e esse id não existir
//estende RunTimeException que compilador nao exige que trate
//em UserSevice implementar o metodo findById
public class ObjectNotFoundException extends RuntimeException {
   
	//gera automatico
	private static final long serialVersionUID = 1L;

	//construtor que recebe uma mensagem como parametro
	public ObjectNotFoundException(String msg) {
		//repasse para a superclasse RuntimeException passando essa mensagem
		super(msg);
	}
}
