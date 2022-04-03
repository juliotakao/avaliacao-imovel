package br.com.meuimovel.domain.filter;

import java.util.stream.Stream;

import br.com.meuimovel.domain.filter.exceptions.TipoFiltroNaoImplementadoException;

public enum TipoFiltroEnum {

	IGUAL("eq","="),	
	MAIOR("gt", ">"),	
	MENOR("lt", "<");
	
	private String valor;
	private String operador;


	TipoFiltroEnum(String valor, String operador) {
		this.valor = valor;
		this.operador = operador;
	}

	public static TipoFiltroEnum getByValor(String valor) {
		return Stream.of(values())
			.filter(op -> String.valueOf(op.valor).equalsIgnoreCase(valor))
			.findFirst()
			.orElseThrow(TipoFiltroNaoImplementadoException::new);
	}
	
	public static TipoFiltroEnum getByOperador(String operador) {
		return Stream.of(values())
			.filter(op -> String.valueOf(op.operador).equalsIgnoreCase(operador))
			.findFirst()
			.orElseThrow(TipoFiltroNaoImplementadoException::new);
	}
	
	@Override
	public String toString() {
		return String.valueOf(valor);
	}
	
}
