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

import app.entity.Funcionario;
import app.entity.Vendas;
import app.repository.FuncionarioRepository;

public class FuncionarioControllerTest {

	@Autowired
	FuncionarioController funcionarioController;

	@MockBean
	FuncionarioRepository repository;

	@BeforeEach
	void setup() {
		List<Funcionario> funcionario = new ArrayList<>();
		funcionario.add(new Funcionario(1L,"Merda",14,12345, null));
		funcionario.add(new Funcionario(1L,"Merda",14,12345, null));
		Funcionario funcionarionovo =	new Funcionario(1L,"Merda",14,12345, null);
		when(this.repository.save(any(Funcionario.class))).thenReturn(funcionarionovo);
		when(this.repository.findAll()).thenReturn(funcionario);
		when(this.repository.findAll()).thenThrow(new RuntimeException("Erro ao listar vendas"));
		when(this.repository.save(any(Funcionario.class))).thenThrow(new RuntimeException("Erro ao Atualizar venda"));
		when(this.repository.delete(999L)).thenThrow(new IllegalArgumentException("Erro ao deletar venda"));
		when(repository.save(any(Funcionario.class))).thenThrow(new RuntimeException("Erro ao salvar venda"));
		when(repository.findById(999L)).thenThrow(new RuntimeException("Erro ao buscar venda"));
		
	}

	// ----------------------------------------------------
	// ------------------------ SAVE ----------------------
	// ----------------------------------------------------
	
	@Test
	@DisplayName("[TESTE] Save")
	void save() {
		Funcionario funcionarionovo = new Funcionario();

		when(repository.save(any(Funcionario.class))).thenReturn(funcionarionovo);
		ResponseEntity<String> funcionariosalvo = funcionarioController.save(funcionarionovo);
		verify(repository, times(1)).save(funcionarionovo);
		assertNotNull(funcionariosalvo);
		
	}

	@Test
	@DisplayName("[EXCEPTION] Save")
	void save_Exception() {
		ResponseEntity<String> response = funcionarioController.save(new Funcionario());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Erro Detectado", response.getBody());
	}
	// ----------------------------------------------------
	// ------------------- LIST ALL -----------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] ListAll")
	void findall() {
		ResponseEntity<List<Funcionario>> response = this.funcionarioController.listAll();
		List<Funcionario> funcionario = response.getBody();

		assertEquals(3, funcionario.size());
	}

	@Test
	@DisplayName("[EXCEPTION] ListAll")
	void findAll_Exception() {
		
		ResponseEntity<List<Funcionario>> response = this.funcionarioController.listAll();
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNull(response.getBody());
	}

	// ----------------------------------------------------
	// -------------------- DELETE ------------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] Delete")
	void delete() {
		ResponseEntity<String> response = this.funcionarioController.delete(1L);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}

	@Test
	@DisplayName("[EXCEPTION] Delete")
	void delete_Exception() {
		
		ResponseEntity<String> response = funcionarioController.delete(999L);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	// ----------------------------------------------------
	// ------------------ Find By Id ----------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] FindById")
	void FindById() {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(funcionario));
		ResponseEntity<Funcionario> response = funcionarioController.findById(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(funcionario, response.getBody());
	}

	@Test
	@DisplayName("[EXCEPTION] FindById")
	void FindById_Exception() {
		
		ResponseEntity<Funcionario> response = funcionarioController.findById(999L);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNull(response.getBody());
	}

	// ----------------------------------------------------
	// -------------------- UPDATE ------------------------
	// ----------------------------------------------------

	@Test
	@DisplayName("[TESTE] Update")
	void update() {
		Funcionario funcionario = new Funcionario(1L,"Merda",14,12345, null);
		ResponseEntity<String> response = this.funcionarioController.update(1L, funcionario);
		int httpStatus = response.getStatusCode().value();
		assertEquals(HttpStatus.OK, httpStatus);
	}

	@Test
	@DisplayName("[EXCEPTION] Update")
	void update_exception() {
		
		ResponseEntity<String> response = this.funcionarioController.update(1L, new Funcionario());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
