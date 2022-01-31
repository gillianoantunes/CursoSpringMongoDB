package com.gillianocampos.workshopmondo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

//classe para encodar a palavra que deseja pesquisar nos post
public class URL {
	//criei metodo static para nao precisar instanciar de nome decodeParam
	//vai retorna chamando uma funçaõ do java que ja existe URLDecoder.decode que exige 2 parametros
	//primeiro é qual texto que deseja codificar e segndo qual padrao de decodificação que quero usar..no caso sera UTF-8 que é o padrao da web
	//vai dar um erro e clica na correção para envolver com try catch
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			//caso ocorrer algum erro retorna a string vazia
			return "";
		}
	}
}
