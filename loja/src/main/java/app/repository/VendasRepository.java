package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Vendas;

public interface VendasRepository extends JpaRepository<Vendas, Long> {

}
