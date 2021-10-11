package br.com.supera.game.store.gamestoredesafio.repository;

import br.com.supera.game.store.gamestoredesafio.entity.CheckoutTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<CheckoutTable, String> {
}