package br.com.meuimovel.domain.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SpecFactory<T> {
	
	private Map<TipoFiltroEnum, Function<SearchCriteria, Specification<T>>> specs;
	
	@PostConstruct
	private void init() {
		specs = new HashMap<>();
		specs.put(TipoFiltroEnum.IGUAL, this::getEqualsSpecification);
		specs.put(TipoFiltroEnum.MAIOR, this::getGreaterThanSpecification);
		specs.put(TipoFiltroEnum.MENOR, this::getLessThanSpecification);
	}
	
	public Specification<T> getByCriteria(SearchCriteria criteria) {
		return specs.get(criteria.getTipoFiltro()).apply(criteria);
	}
	
	private Specification<T> getEqualsSpecification(SearchCriteria criteria) {
		return (root, query, builder) -> {
			if (root.get(criteria.getChave()).getJavaType() == String.class) {
                return builder.like(
                  root.<String>get(criteria.getChave()), "%" + criteria.getValor() + "%");
            } else {
                return builder.equal(root.get(criteria.getChave()), criteria.getValor());
            }
		};
	}
	
	private Specification<T> getGreaterThanSpecification(SearchCriteria criteria) {
		return (root, query, builder) -> {
			return builder
				.greaterThan(root.<String> get(criteria.getChave()), criteria.getValor().toString());
		};
	}
	
	private Specification<T> getLessThanSpecification(SearchCriteria criteria) {
		return (root, query, builder) -> {
			return builder
				.lessThan(root.<String> get(criteria.getChave()), criteria.getValor().toString());
		};
	}

}
