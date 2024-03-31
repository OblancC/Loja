package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import app.entity.Produto;
import app.entity.Vendas;
import app.repository.VendasRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VendasServiceTest {
	
	//-------------------------------------------------------------------------
	//Save  -------------------------------------------------------------------
	//-------------------------------------------------------------------------
	
	@Test
	@DisplayName("[Teste] Salvar")
	void testSave() {
		Vendas vendas = new Vendas();
		vendas.setDataVenda("2024-03-30");
		vendas.setValorTotal(100.0);
		
		when(repository.save(any(Vendas.class))).thenReturn(vendas);
		
		String resultado = vendasService.save(vendas);
		verify(repository, times(1)).save(vendas);
		assertEquals("Vendas salva com sucesso", resultado);
	}
	
	@Test
	@DisplayName("[Exception] Salvar - Data de Venda nula")
	void testSaveException() {
		assertThrows(NullPointerException.class, ()->{
			vendasService.save(null);
		});
	}
	
	
	//-------------------------------------------------------------------------
	//FindById ----------------------------------------------------------------
	//-------------------------------------------------------------------------
	
	@Test
	@DisplayName("[Teste] Find by Id")
	void findById() {
		Vendas value = new Vendas();
		value.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(value));
		
		Vendas byId = vendasService.findById(1L);
		
		assertEquals(1L, byId.getId());
		assertEquals(value,byId);
		assertNotNull(byId);
	}
	
	@Test
	@DisplayName("[Exception] Find by Id")
	void findByIDException() {
		when(repository.findById(1L)).thenReturn(Optional.empty());
		
		assertThrows(RuntimeException.class, () ->{
			vendasService.findById(1L);
		});
	}
	
	//-------------------------------------------------------------------------
	//Delete ------------------------------------------------------------------
	//-------------------------------------------------------------------------
	
	@Test
	@DisplayName("[Teste] Delete")
	void TestDelete() {
		vendasService.delete(1L);
		verify(repository, times(1)).deleteById(1L);
	}
	
	@Test
	@DisplayName("[Exception] Delete - ID nulo")
	void TestDeleteIdNulo() {
		doThrow(IllegalArgumentException.class).when(repository).deleteById(null);
		assertThrows(Exception.class, () ->{
			vendasService.delete(null);
		});

		verify(repository, times(1)).deleteById(any());
	}
	//-------------------------------------------------------------------------
	//Update ------------------------------------------------------------------
	//-------------------------------------------------------------------------
	
	@Test
	@DisplayName("[Teste] Update")
	void TestUpdate() {
		
	}
	
	//-------------------------------------------------------------------------
	//CalcularValorTotal-------------------------------------------------------
	//-------------------------------------------------------------------------
	
	@Test
	@DisplayName("[Teste] Calcular Valor Total")
	void calcularValorTotal() {
		List<Produto> produtos = new ArrayList<>();
		produtos.add(new Produto(1L,"Testamento",25.25,"Sapato"));
		produtos.add(new Produto(1L,"lavanderia",25.25,"Cavalo"));
		produtos.add(new Produto(1L,"farmacia",25.25,"Teste"));
		produtos.add(new Produto(1L,"teste",24.25,"Sapato"));
		
		double result = vendasService.calcularValorTotal(produtos);
		assertEquals(100.0,result);
	}
	
	@Test
	@DisplayName("[Exception] Calcular valor Total")
	void calcularValorTotalNomeEmBranco() {
		List<Produto> produtos = new ArrayList<>();

		double totalValue = vendasService.calcularValorTotal(produtos);
		assertEquals(0.0, totalValue);
	}
	//-------------------------------------------------------------------------
	//VerificarStatus---------------------------------------------------------
	//-------------------------------------------------------------------------
	
	@Test
	@DisplayName("[Teste]Verificar o Status do Produto")
	void verificarStatus() {
		
	}
	
	@Mock
	private VendasRepository repository;
	
	@InjectMocks
	private VendasService vendasService;
}
