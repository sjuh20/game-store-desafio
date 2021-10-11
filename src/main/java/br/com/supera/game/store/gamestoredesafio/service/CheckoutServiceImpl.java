package br.com.supera.game.store.gamestoredesafio.service;

import br.com.supera.game.store.gamestoredesafio.entity.ProductTable;
import br.com.supera.game.store.gamestoredesafio.model.CheckoutModel;
import br.com.supera.game.store.gamestoredesafio.model.ProductModel;
import br.com.supera.game.store.gamestoredesafio.repository.CheckoutRepository;
import br.com.supera.game.store.gamestoredesafio.repository.ProductRepository;
import br.com.supera.game.store.gamestoredesafio.utils.CompareFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final ProductRepository productRepository;
    private static final long VALUE_FREIGHT_FREE = 250L;
    private static final long VALUE_FREIGHT = 10L;

    public CheckoutServiceImpl(CheckoutRepository checkoutRepository, ProductRepository productRepository) {
        this.checkoutRepository = checkoutRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CheckoutModel createCheckout(List<Long> ids) {
        List<ProductModel> products = productRepository
                .findAllById(ids)
                .stream().map(ProductTable::toModel)
                .collect(Collectors.toList());

        List<BigDecimal> values = getTotalProductValues(products);
        CheckoutModel model = new CheckoutModel(UUID.randomUUID().toString(), new ArrayList<>());
        CheckoutModel result = checkoutRepository.save(model.toTable()).toModel();
        calculateValues(values, result);
        return result;
    }

    private void calculateValues(List<BigDecimal> values, CheckoutModel result) {
        BigDecimal freight = calculateFreight(values);
        BigDecimal subtotal = calculateSubtotal(values);
        result.setFreight(freight);
        result.setSubTotal(subtotal);
        result.setTotal(calculateTotal(freight, subtotal));
    }

    @Override
    public CheckoutModel addProduct(String checkoutID, long productId, String sort) {
        ProductModel productModel = productRepository.findById(productId).get().toModel();
        CheckoutModel checkoutModel = checkoutRepository.findById(checkoutID).get().toModel();

        List<ProductModel> products = checkoutModel.getProducts();
        products.add(productModel);
        Collections.sort(products, CompareFactory.getComparator(sort));
        return getCheckoutModel(checkoutModel, products);
    }

    @Override
    public CheckoutModel removeProduct(String checkoutID, long productId, String sort) {
        CheckoutModel checkoutModel = checkoutRepository.findById(checkoutID).get().toModel();

        for (ProductModel product : checkoutModel.getProducts()) {
            if (product.getId() == productId) {
                checkoutModel.getProducts().remove(product);
                break;
            }
        }

        checkoutModel.setProducts(checkoutModel.getProducts());
        CheckoutModel result = checkoutRepository.save(checkoutModel.toTable()).toModel();

        List<BigDecimal> values = getTotalProductValues(checkoutModel.getProducts());
        calculateValues(values, result);
        return result;
    }

    @Override
    public CheckoutModel getCheckout(String checkoutId) {
        return checkoutRepository.getById(checkoutId).toModel();
    }

    private CheckoutModel getCheckoutModel(CheckoutModel checkoutModel, List<ProductModel> products) {
        checkoutModel.setProducts(products);
        CheckoutModel result = checkoutRepository.save(checkoutModel.toTable()).toModel();

        List<BigDecimal> values = getTotalProductValues(products);

        calculateValues(values, result);

        return result;
    }

    private List<BigDecimal> getTotalProductValues(List<ProductModel> products) {
        return products
                .stream().map(model -> model.getPrice())
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal calculateFreight(List<BigDecimal> values) {

        if ((BigDecimal.valueOf(VALUE_FREIGHT_FREE).compareTo(totalValueProducts(values)) > 0)) {
            return BigDecimal.valueOf(values.size() * VALUE_FREIGHT);
        } else
            return new BigDecimal(0);
    }

    @Override
    public BigDecimal calculateSubtotal(List<BigDecimal> values) {
        return totalValueProducts(values);
    }

    @Override
    public BigDecimal calculateTotal(BigDecimal freight, BigDecimal subtotal) {
        return freight.add(subtotal);
    }

    @Override
    public BigDecimal totalValueProducts(List<BigDecimal> values) {
        return values.stream().filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
