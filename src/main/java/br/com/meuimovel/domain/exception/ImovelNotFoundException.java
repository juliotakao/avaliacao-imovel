package br.com.meuimovel.domain.exception;

public class ImovelNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6103693919267090122L;

	public ImovelNotFoundException(Long id) {
		    super("Imovel nao encontrado id=" + id);
		  }
}

