package br.com.meuimovel.filter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED, reason = "error.nao.implementado")
public class TipoFiltroNaoImplementadoException extends RuntimeException {
	private static final long serialVersionUID = 8248276973416703821L;	

}
