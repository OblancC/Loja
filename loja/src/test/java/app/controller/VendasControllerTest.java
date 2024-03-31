package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
		vendas.add(new Vendas(1L, 1.0,"2023-12-22","teste","EM ANDAMENTO", null, null, null));
		vendas.add(new Vendas(1L, 1.0,"2023-12-22","teste","EM ANDAMENTO", null, null, null));
		
		when(this.repository.findAll()).thenReturn(vendas);
	}
	
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
	@DisplayName("[TESTE] ListAll")
	void findall() {
		ResponseEntity<List<Vendas>> response = this.vendasController.listAll();
		List<Vendas> vendas = response.getBody();
		
		assertEquals(2,vendas.size());
	}
	
}
