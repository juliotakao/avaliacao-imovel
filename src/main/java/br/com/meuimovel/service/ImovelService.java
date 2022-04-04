package br.com.meuimovel.service;

import java.util.List;

import br.com.meuimovel.model.Imovel;

public interface ImovelService {

	List<Imovel> listar(String filter, Integer pageNumber, Integer pageSize, String sort);
}
