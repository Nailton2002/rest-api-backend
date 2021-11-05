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

import br.com.springboot.model.Produto;
import br.com.springboot.repository.ProdutoRepository;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Produto>> findById(@PathVariable Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> produtos = produtoRepository.findAll();
		return ResponseEntity.ok().body(produtos);
	}

	@PostMapping
	public ResponseEntity<Produto> save(@RequestBody Produto produto) {
		Produto obj = produtoRepository.save(produto);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> update(@PathVariable(value = "id") Integer id, @RequestBody @Valid Produto produto) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if (!optional.isPresent()) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		} else {
			produto.setId(optional.get().getId());
			return new ResponseEntity<Produto>(produtoRepository.save(produto), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isPresent()) {
			produtoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
