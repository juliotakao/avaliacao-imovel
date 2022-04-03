package br.com.meuimovel.api.v1.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.meuimovel.domain.exception.ImovelNotFoundException;
import br.com.meuimovel.domain.model.Imovel;
import br.com.meuimovel.domain.repository.ImovelRepository;
import br.com.meuimovel.domain.service.ImovelService;

@RestController
@RequestMapping(path = "/api/v1/imoveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImovelController {

	@Autowired
	private ImovelService imovelService;

	@Autowired
	private ImovelRepository imovelRepository;

	@GetMapping
	public ResponseEntity<List<Imovel>> listar(@RequestParam(defaultValue = "", required = false) String filter,
			@RequestParam(defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(required = false) String sort) {
		List<Imovel> lista = imovelService.listar(filter, pageNumber, pageSize, sort);
		if (lista.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Imovel> obter(@PathVariable long id) {
		Optional<Imovel> opImovel = imovelRepository.findById(id);

		if (!opImovel.isPresent())
			throw new ImovelNotFoundException(id);

		Imovel imovel = opImovel.get();
		
		imovel.add(linkTo(methodOn(this.getClass()).listar("", 1, 10, "id desc")).withRel("GET"));
		imovel.add(linkTo(this.getClass()).slash(id).withSelfRel().withRel("DELETE"));

		return new ResponseEntity<>(imovel, HttpStatus.OK);
	}

	@PostMapping
	public Imovel criar(@RequestBody Imovel newImovel) {
        return imovelRepository.save(newImovel);
    }

	 @PutMapping("/{id}")
	 public Imovel alterar(@RequestBody Imovel newImovel, @PathVariable Long id) {

        return imovelRepository.findById(id)
            .map(imovel -> {
            	imovel.setDescricao(newImovel.getDescricao());
               
                return imovelRepository.save(imovel);
            })
            .orElseGet(() -> {
                return imovelRepository.save(newImovel);
            });
    }

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		imovelRepository.deleteById(id);
	}

}
