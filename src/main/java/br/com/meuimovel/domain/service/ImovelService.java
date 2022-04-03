package br.com.meuimovel.domain.service;

import java.util.List;

import br.com.meuimovel.domain.model.Imovel;

public interface ImovelService {

	List<Imovel> listar(String filter, Integer pageNumber, Integer pageSize, String sort);

	Imovel incluir(Imovel imovel);

	Imovel obter(Long idImovel);

	Imovel atualizar(Long idImovel, Imovel imovel);

	void remover(Long idImovel);
}
