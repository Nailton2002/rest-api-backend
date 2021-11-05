package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
