package br.com.meuimovel.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Imovel extends RepresentationModel<Imovel>{
	               
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min = 0, max = 50)
	@Column(nullable = false, length = 50)
    private String descricao;
	
	@NotEmpty
	@Size(min = 0, max = 250)
	@Column(nullable = false, length = 250)
    private String logradouro;
	
	@NotEmpty
	@Size(min = 0, max = 15)
	@Column(nullable = false, length = 15)
    private String numero;
	
	@NotEmpty
	@Size(min = 0, max = 50)
	@Column(nullable = false, length = 50)
    private String bairro;	  
	
	@NotEmpty
	@Size(min = 0, max = 50)
	@Column(nullable = false, length = 50)
    private String cidade;
	
	@NotEmpty
	@Size(min = 0, max = 2)
	@Column(nullable = false, length = 2)
    private String uf;	
	
	@Digits(integer = 3, fraction = 0)
	@Column(precision = 3, scale = 0)
    private Integer dormitorio;
	
	@Digits(integer = 3, fraction = 0)
	@Column(precision = 3, scale = 0)
    private Integer suite;
	
	@Digits(integer = 8, fraction = 0)
	@Column( precision = 8, scale = 0)
    private Integer area;

	@Digits(integer = 20, fraction = 2)
	@Column(precision = 20, scale = 2)
    private BigDecimal valor;		
}
