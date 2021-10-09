package br.com.supera.game.store.gamestoredesafio.repository;

import br.com.supera.game.store.gamestoredesafio.entity.ProductTable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductRepository extends JpaRepository<ProductTable, Long> {

    List<ProductTable> findAll();
    List<ProductTable> findAllByOrderByPriceAsc() ;
    List<ProductTable> findAllByOrderByScoreAsc();
    List<ProductTable> findAllByOrderByNameAsc();


}