package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
