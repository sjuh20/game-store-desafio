package br.com.supera.game.store.gamestoredesafio.service;

import br.com.supera.game.store.gamestoredesafio.model.CheckoutModel;
import br.com.supera.game.store.gamestoredesafio.model.ProductModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface CheckoutService {

    public CheckoutModel createCheckout(List<Long> ids);

    public CheckoutModel addProduct(String checkoutID, long productId, String sort);

    public CheckoutModel removeProduct(String checkoutID, long productId, String sort);

    public CheckoutModel getCheckout(String checkoutId);

    public BigDecimal calculateFreight(List<BigDecimal> values);

    public BigDecimal calculateSubtotal(List<BigDecimal> values);

    public BigDecimal calculateTotal(BigDecimal freight, BigDecimal subtotal);

    public BigDecimal totalValueProducts(List<BigDecimal> values);
}
