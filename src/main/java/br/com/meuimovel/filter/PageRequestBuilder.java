package br.com.meuimovel.filter;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class PageRequestBuilder {

	private PageRequestBuilder() {
		
	}

	public static PageRequest getPageRequest(Integer pageNumber, Integer pageSize,  String sortingCriteria) {
		var sortingFileds = new LinkedHashSet<>(
				Arrays.asList(StringUtils.split(StringUtils.defaultIfEmpty(sortingCriteria, ""), ",")));

		var sortingOrders = sortingFileds.stream().map(PageRequestBuilder::getOrder)
				.collect(Collectors.toList());

		var sort = sortingOrders.isEmpty() ? null : Sort.by(sortingOrders);

		if(sort != null) {
			return PageRequest.of(ObjectUtils.defaultIfNull(pageNumber, 1) - 1, ObjectUtils.defaultIfNull(pageSize, 20),sort);
		} else {
			return PageRequest.of(ObjectUtils.defaultIfNull(pageNumber, 1) - 1, ObjectUtils.defaultIfNull(pageSize, 20));
		}
	}

	private static Order getOrder(String value) {
		if (StringUtils.endsWith(value.toUpperCase(), Direction.DESC.toString())) {
			return new Order(Direction.DESC, StringUtils.substringBefore(value, " "));
		} else if (StringUtils.endsWith(value.toUpperCase(), Direction.ASC.toString())) {
			return new Order(Direction.ASC, StringUtils.substringBefore(value, " "));
		}else {
			return new Order(Direction.ASC, StringUtils.substringBefore(value, " "));
		}
		
	}

}