package com.store.service;

import com.store.model.*;
import com.store.repos.*;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Value
@AllArgsConstructor
public class BasketService {

    ProductService productService;

    BasketRepo basketRepo;

    ProductInBasketRepo productInBasketRepo;

    OrderrRepo orderrRepo;

    ProductInOrderRepo productInOrderRepo;

    UserRepo userRepo;

    public void addProduct(User user, Integer productId) {
        Integer basketId = user.getBasket().getId();
        List<ProductInBasket> productInBaskets = productInBasketRepo.findByBasket_Id(basketId);
        boolean contain_result = true;
        for (ProductInBasket p : productInBaskets) {
            if (p.getProductId().equals(productId)) {
                contain_result = false;
                break;
            }
        }
        if (contain_result) {
            Product product = productService.findById(productId);
            Basket basket = basketRepo.findById(user.getBasket().getId());
            ProductInBasket productInBasket = new ProductInBasket(product, 1);
            productInBasket.setBasket(basket);
            basket.addProduct(productInBasket);
            basketRepo.save(basket);
            productInBasketRepo.save(productInBasket);
        }
    }

    public void deleteProduct(Integer basketId, Integer id) {
        ProductInBasket productInBasket = productInBasketRepo.findById(id);
        productInBasketRepo.removeById(id);
        Basket basket = basketRepo.findById(basketId);
        basket.deleteProductInBasket(productInBasket);
        basketRepo.save(basket);
    }

    public Integer increment(Integer id) {
        ProductInBasket productInBasket = productInBasketRepo.findById(id);
        productInBasket.increment();
        productInBasketRepo.save(productInBasket);
        return productInBasket.getCount();
    }

    public Basket findBasketById(Integer id) {
        return basketRepo.findById(id);
    }

    public Integer decrement(Integer id) {
        ProductInBasket productInBasket = productInBasketRepo.findById(id);
        productInBasket.decrement();
        productInBasketRepo.save(productInBasket);
        return productInBasket.getCount();
    }

    public ProductInBasket getProductById(Integer id) {
        return productInBasketRepo.findById(id);
    }

    public Integer getTotalBasketSum(Integer id) {
        Integer summ = 0;
        for (ProductInBasket p : productInBasketRepo.findByBasket_Id(id)) {
            summ += p.getProductTotalPrice();
        }
        return summ;
    }

    public List<String> checkBeforePay(User user) {
        List<String> model = new ArrayList<>();
        Integer balance = user.getBalance();
        Integer totalInBasket = getTotalBasketSum(user.getBasket().getId());
        if (balance < totalInBasket) {
            model.add("На счету недостаточно средств");
        }
        for (ProductInBasket p : productInBasketRepo.findByBasket_Id(user.getBasket().getId())) {
            Integer productId = p.getProductId();
            if (productService.findById(productId).getCount() <= p.getCount()) {
                model.add("товар № " + p.getProductId() + " недостаточное кол-во на складе");
            }
        }
        return model;
    }

    public void pay(User user) {
        Orderr orderr = new Orderr();
        orderr.setUser(user);
        Integer balance = user.getBalance();
        balance -= getTotalBasketSum(user.getBasket().getId());
        user.setBalance(balance);
        user.addOrder(orderr);
        orderr.setSumm(getTotalBasketSum(user.getBasket().getId()));
        orderrRepo.save(orderr);
        userRepo.save(user);
        for (ProductInBasket p : productInBasketRepo.findByBasket_Id(user.getBasket().getId())) {
            ProductInOrder productInOrder = new ProductInOrder(
                    orderr, p.getProductName(), p.getProductTotalPrice(), p.getCount()
            );
            productInOrderRepo.save(productInOrder);
            orderr.addProduct(productInOrder);
            productInBasketRepo.removeById(p.getId());
        }
    }
}
