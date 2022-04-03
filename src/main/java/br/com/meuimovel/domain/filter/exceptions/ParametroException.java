package br.com.meuimovel.domain.filter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.parametro")
public class ParametroException extends RuntimeException {
	private static final long serialVersionUID = -7238051081029803780L;
}
