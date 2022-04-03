package br.com.meuimovel.domain.filter;

import lombok.Data;

@Data
public class SearchCriteria {

	private String chave;
    private TipoFiltroEnum tipoFiltro;
    private Object valor;
    
    public SearchCriteria(String chave, String tipoFiltro, Object valor) {
    	this.chave = String.valueOf(chave).toLowerCase();
    	this.tipoFiltro = TipoFiltroEnum.getByOperador(tipoFiltro);
   		this.valor = String.valueOf(valor).toUpperCase();    	    	    	
    }
}