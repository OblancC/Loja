package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Produto;
import app.repository.ProdutoRepository;

public class ProdutoControllerTest {

	@Autowired
	ProdutoController produtoController;

	@MockBean
	ProdutoRepository repository;

	@BeforeEach
	void setup() {
		List<Produto> produto = new ArrayList<>();
		produto.add(new Produto(1L, "Teste",2.0,"testamento"));
		produto.add(new Produto(1L, "Teste",2.0,"testamento"));
		Produto produtosalvo = new Produto(1L, "Teste",2.0,"testamento");

		when(repository.save(any(Produto.class))).thenReturn(produtosalvo);
		when(repository.findAll()).thenReturn(produto);
		when(repository.findById(1L)).thenReturn(Optional.ofNullable(produto.get(0)));
		when(repository.findAll()).thenThrow(new RuntimeException("Erro ao listar vendas"));
		when(repository.save(any(Produto.class))).thenThrow(new RuntimeException("Erro ao Atualizar venda"));
		when(produtoController.delete(999L)).thenThrow(new RuntimeException("Erro ao deletar venda"));
		when(repository.save(any(Produto.class))).thenThrow(new RuntimeException("Erro ao salvar venda"));
		when(repository.findById(999L)).thenThrow(new RuntimeException("Erro ao buscar venda"));
	}

	// ----------------------------------------------------
	// ------------------------ SAVE ----------------------
	// ----------------------------------------------------
	@Test
	@DisplayName("[TESTE] Save")
	void save() {
		Produto produtonovo = new Produto();

		
		ResponseEntity<String> produtosalvo = produtoController.save(produtonovo);
		verify(repository, times(1)).save(produtonovo);
		assertNotNull(produtosalvo);
	}

	@Test
	@DisplayName("[EXCEPTION] Save")
	void save_Exception() {
		ResponseEntity<String> response = produtoController.save(new Produto());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Erro Detectado", response.getBody());
	}
	// ----------------------------------------------------
	// ------------------- LIST ALL -----------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] ListAll")
	void findall() {
		ResponseEntity<List<Produto>> response = this.produtoController.listAll();
		List<Produto> produtos = response.getBody();

		assertEquals(3, produtos.size());
	}

	@Test
	@DisplayName("[EXCEPTION] ListAll")
	void findAll_Exception() {
		
		ResponseEntity<List<Produto>> response = this.produtoController.listAll();
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNull(response.getBody());
	}

	// ----------------------------------------------------
	// -------------------- DELETE ------------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] Delete")
	void delete() {
		ResponseEntity<String> response = this.produtoController.delete(1L);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}

	@Test
	@DisplayName("[EXCEPTION] Delete")
	void delete_Exception() {
		
		ResponseEntity<String> response = produtoController.delete(999L);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	// ----------------------------------------------------
	// ------------------ Find By Id ----------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] FindById")
	void FindById() {
		Produto produto = new Produto();
		produto.setId(1L);
		
		ResponseEntity<Produto> response = produtoController.findById(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(produto, response.getBody());
	}

	@Test
	@DisplayName("[EXCEPTION] FindById")
	void FindById_Exception() {
		
		ResponseEntity<Produto> response = produtoController.findById(999L);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNull(response.getBody());
	}

	// ----------------------------------------------------
	// -------------------- UPDATE ------------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] Update")
	void update() {
		Produto produto = new Produto();
		produto.setId(1L);
		ResponseEntity<String> response = this.produtoController.update(1L, produto);
		int httpStatus = response.getStatusCode().value();
		assertEquals(HttpStatus.OK, httpStatus);
	}

	@Test
	@DisplayName("[EXCEPTION] Update")
	void update_exception() {
		
		ResponseEntity<String> response = this.produtoController.update(1L, new Produto());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	// ----------------------------------------------------
	// ---------------- Find By Endereco ------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] FindByNome")
	void findByEndereco() {
		ResponseEntity<List<Produto>> response = this.produtoController.findByNome("Teste");
		List<Produto> produto = response.getBody();
		assertEquals(2, produto.size());
	}

	// ----------------------------------------------------
	// ---------------- Find By Categoria  ----------------
	// ----------------------------------------------------
	
	@Test
	@DisplayName("[TESTE] FindByCategoria")
	void findByCategoria() {
		ResponseEntity<List<Produto>> response = this.produtoController.findByCategoria("testamento");
		List<Produto> produto = response.getBody();
		assertEquals(2, produto.size());
	}
	
	// ----------------------------------------------------
	// ---------------- Find By Valor  --------------------
	// ----------------------------------------------------
	
	@Test
	@DisplayName("[TESTE] FindByValor")
	void findByValor() {
		ResponseEntity<List<Produto>> response = this.produtoController.findByValor(2.0);
		List<Produto> produto = response.getBody();
		assertEquals(2, produto.size());
	}
}
