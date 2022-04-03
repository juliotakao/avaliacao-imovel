package br.com.meuimovel.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.meuimovel.domain.model.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long>, JpaSpecificationExecutor<Imovel> {

}
