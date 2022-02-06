package com.gillianocampos.workshopmondo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
	
	//metodo para tratar datas recebidas que recebe uma data na forma de string e uma data padrão caso der erro datapadrao
	public static Date convertedata(String textDate, Date dataPadrao) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//considerar a data no padrao grewinch mas tem que gente que gosta de pegar o padrao de data do computador do usuario
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		//retorna data que pode gerar exceção, se gerar retornar uma data padrao do paramentro coloar try cat
		try {
			return sdf.parse(textDate);
		} catch (ParseException e) {
			//se der exceção retorna a data padrao
			return dataPadrao;
		}
	}
}
