package br.com.meuimovel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

import br.com.meuimovel.filter.PageRequestBuilder;
import br.com.meuimovel.filter.SpecBuilder;
import br.com.meuimovel.model.Imovel;
import br.com.meuimovel.repository.ImovelRepository;

@Service
@Transactional
@ConditionalOnSingleCandidate(br.com.meuimovel.service.ImovelService.class)
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
}
