package br.com.springboot.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.Product;
import br.com.springboot.repository.ProductRepository;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Product>> findById(@PathVariable Integer id) {
		Optional<Product> product = productRepository.findById(id);
		return ResponseEntity.ok().body(product);
	}

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> product = productRepository.findAll();
		return ResponseEntity.ok().body(product);
	}

	@PostMapping
	public ResponseEntity<Product> save(@RequestBody Product product) {
		Product obj = productRepository.save(product);
		return ResponseEntity.ok().body(obj);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable(value = "id") Integer id, @RequestBody @Valid Product product) {
		Optional<Product> optional = productRepository.findById(id);
		if (!optional.isPresent()) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		} else {
			product.setId(optional.get().getId());
			return new ResponseEntity<Product>(productRepository.save(product), HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
