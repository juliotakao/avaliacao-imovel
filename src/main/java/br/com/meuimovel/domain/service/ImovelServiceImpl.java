package br.com.meuimovel.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

import br.com.meuimovel.domain.filter.PageRequestBuilder;
import br.com.meuimovel.domain.filter.SpecBuilder;
import br.com.meuimovel.domain.model.Imovel;
import br.com.meuimovel.domain.repository.ImovelRepository;

@Service
@Transactional
@ConditionalOnSingleCandidate(br.com.meuimovel.domain.service.ImovelService.class)
public class ImovelServiceImpl implements ImovelService {

	@Autowired
	private ImovelRepository imovelRepository;

	@Autowired
	private SpecBuilder<Imovel> specBuilder;

	@Override
	public List<Imovel> listar(String filter, Integer pageNumber, Integer pageSize, String sort) {
		try {
			var spec = StringUtils.isNoneEmpty(filter) ? specBuilder.with(filter).build() : null;
			var page = PageRequestBuilder.getPageRequest(pageNumber, pageSize, sort);

			return imovelRepository.findAll(spec, page).getContent();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	@Override
	public Imovel obter(Long id) {
		return imovelRepository.getById(id);
	}
	
	@Override
	public Imovel incluir(Imovel imovel) {
		return imovelRepository.save(imovel);		
	}
	
	@Override
	public Imovel atualizar(Long idImovel, Imovel imovel) {
		return imovelRepository.save(imovel);		
	}
	
	@Override
	public void remover(Long id) {
		imovelRepository.deleteById(id);		
	}

}
