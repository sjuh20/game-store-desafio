package br.com.supera.game.store.gamestoredesafio.service;

import br.com.supera.game.store.gamestoredesafio.entity.ProductTable;
import br.com.supera.game.store.gamestoredesafio.model.ProductModel;
import br.com.supera.game.store.gamestoredesafio.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public ProductModel registerProduct(ProductModel product) {
        return repository.save(product.toTable()).toModel();
    }

    @Override
    public void registerAllProducts(List<ProductModel> products) {
        repository.saveAll(products.stream().map(ProductModel::toTable).collect(Collectors.toList()));
    }

    @Override
    public List<ProductModel> listProducts(String sort) {
        switch (sort) {
            case "price":
                return repository.findAllByOrderByPriceAsc().
                        stream().
                        map(ProductTable::toModel).
                        collect(Collectors.toList());
            case "score":
                return repository.findAllByOrderByScoreAsc().
                        stream().
                        map(ProductTable::toModel).
                        collect(Collectors.toList());
            case "alphabetic":
                return repository.findAllByOrderByNameAsc().
                        stream().
                        map(ProductTable::toModel).
                        collect(Collectors.toList());
            default:
                return repository.findAll().
                        stream().
                        map(ProductTable::toModel).
                        collect(Collectors.toList());
        }

    }
}
