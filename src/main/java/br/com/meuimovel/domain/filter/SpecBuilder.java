package br.com.meuimovel.domain.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.meuimovel.domain.filter.exceptions.ParametroException;

@Component
public class SpecBuilder<T> {
	
	@Autowired
	private SpecFactory<T> specFactory;
     
    private List<SearchCriteria> listaSearchCriteria;
    
    public SpecBuilder<T> with(String query) {
    	listaSearchCriteria = new ArrayList<SearchCriteria>();
    	Pattern pattern = Pattern.compile("(\\w+?)(=|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(query + ",");
           
        
        while (matcher.find()) {
        	listaSearchCriteria.add(new SearchCriteria(
        		matcher.group(1),
        		matcher.group(2),
        		matcher.group(3)
        	));
        }
        return this;
    }
 
	public Specification<T> build() {
        if (listaSearchCriteria.size() == 0) {
            throw new ParametroException();
        }
 
        var specs = listaSearchCriteria.stream()
          .map(specFactory::getByCriteria)
          .collect(Collectors.toList());
         
        var result = specs.get(0);
 
        for (int i = 1; i < listaSearchCriteria.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        } 
        return result;
    }
}
