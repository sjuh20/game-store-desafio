package br.com.supera.game.store.gamestoredesafio.service;

import br.com.supera.game.store.gamestoredesafio.model.ProductModel;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  interface ProductService {

    public ProductModel registerProduct(ProductModel product);
    public void registerAllProducts(List<ProductModel> products);
    public List<ProductModel> listProducts(String sort);

}
