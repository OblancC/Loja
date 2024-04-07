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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Vendas;
import app.repository.VendasRepository;

@SpringBootTest
public class VendasControllerTest {

	@Autowired
	VendasController vendasController;

	@MockBean
	VendasRepository repository;

	@BeforeEach
	void setup() {
		List<Vendas> vendas = new ArrayList<>();
		vendas.add(new Vendas(1L, 1.0, "2023-12-22", "teste", "EM ANDAMENTO", null, null, null));
		vendas.add(new Vendas(1L, 1.0, "2023-12-22", "teste", "EM ANDAMENTO", null, null, null));
		Vendas vendaSalva = new Vendas(1L, 1.0, "2023-12-22", "teste", "EM ANDAMENTO", null, null, null);

		when(this.repository.save(any(Vendas.class))).thenReturn(vendaSalva);
		when(this.repository.findAll()).thenReturn(vendas);
		when(this.repository.findAll()).thenThrow(new RuntimeException("Erro ao listar vendas"));
		when(this.repository.findByEnderecoEntrega("teste")).thenReturn(vendas);
		when(this.repository.findByDataVenda("2023-12-22")).thenReturn(vendas);
		when(this.repository.save(any(Vendas.class))).thenThrow(new RuntimeException("Erro ao Atualizar venda"));
		when(vendasController.delete(999L)).thenThrow(new RuntimeException("Erro ao deletar venda"));
		when(repository.save(any(Vendas.class))).thenThrow(new RuntimeException("Erro ao salvar venda"));
		when(this.repository.findByDataVenda("2023-12-23")).thenReturn(vendas);
		when(repository.findById(999L)).thenThrow(new RuntimeException("Erro ao buscar venda"));
		
	}

	// ----------------------------------------------------
	// ------------------------ SAVE ----------------------
	// ----------------------------------------------------
	
	@Test
	@DisplayName("[TESTE] Save")
	void save() {
		Vendas novavenda = new Vendas();

		when(repository.save(any(Vendas.class))).thenReturn(novavenda);
		ResponseEntity<String> vendaSalva = vendasController.save(novavenda);
		verify(repository, times(1)).save(novavenda);
		assertNotNull(vendaSalva);
		
	}

	@Test
	@DisplayName("[EXCEPTION] Save")
	void save_Exception() {
		ResponseEntity<String> response = vendasController.save(new Vendas());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Erro Detectado", response.getBody());
	}
	// ----------------------------------------------------
	// ------------------- LIST ALL -----------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] ListAll")
	void findall() {
		ResponseEntity<List<Vendas>> response = this.vendasController.listAll();
		List<Vendas> vendas = response.getBody();

		assertEquals(3, vendas.size());
	}

	@Test
	@DisplayName("[EXCEPTION] ListAll")
	void findAll_Exception() {
		
		ResponseEntity<List<Vendas>> response = this.vendasController.listAll();
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNull(response.getBody());
	}

	// ----------------------------------------------------
	// -------------------- DELETE ------------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] Delete")
	void delete() {
		ResponseEntity<String> response = this.vendasController.delete(1L);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}

	@Test
	@DisplayName("[EXCEPTION] Delete")
	void delete_Exception() {
		
		ResponseEntity<String> response = vendasController.delete(999L);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	// ----------------------------------------------------
	// ------------------ Find By Id ----------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] FindById")
	void FindById() {
		Vendas vendas = new Vendas();
		vendas.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(vendas));
		ResponseEntity<Vendas> response = vendasController.findById(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(vendas, response.getBody());
	}

	@Test
	@DisplayName("[EXCEPTION] FindById")
	void FindById_Exception() {
		
		ResponseEntity<Vendas> response = vendasController.findById(999L);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNull(response.getBody());
	}

	// ----------------------------------------------------
	// -------------------- UPDATE ------------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] Update")
	void update() {
		Vendas vendas = new Vendas(1L, 1.0, "2023-12-22", "teste", "EM ANDAMENTO", null, null, null);
		ResponseEntity<String> response = this.vendasController.update(1L, vendas);
		int httpStatus = response.getStatusCode().value();
		assertEquals(HttpStatus.OK, httpStatus);
	}

	@Test
	@DisplayName("[EXCEPTION] Update")
	void update_exception() {
		
		ResponseEntity<String> response = this.vendasController.update(1L, new Vendas());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	// ----------------------------------------------------
	// ---------------- Find By Endereco ------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] FindByEndereco")
	void findByEndereco() {
		ResponseEntity<List<Vendas>> response = this.vendasController.findByEnderecoEntrega("teste");
		List<Vendas> vendas = response.getBody();
		assertEquals(2, vendas.size());
	}

	// ----------------------------------------------------
	// ---------------- Find By DataVendas ----------------
	// ----------------------------------------------------
	
	@Test
	@DisplayName("[TESTE] FindByDataVendas")
	void findByDataVendas() {
		ResponseEntity<List<Vendas>> response = this.vendasController.findByDataVenda("2023-12-22");
		List<Vendas> vendas = response.getBody();
		assertEquals(2, vendas.size());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@DisplayName("[TESTE] FindByDataVendas")
	void findByDataVendasTwo() {
		ResponseEntity<List<Vendas>> response = this.vendasController.findByDataVenda("2000-12-22");
		List<Vendas> vendas = response.getBody();
		assertEquals(0, vendas.size());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@DisplayName("[EXCEPTION] FindByDataVendas")
	void findByValorVendas() {
		ResponseEntity<List<Vendas>> response = vendasController.findByDataVenda(null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
}
