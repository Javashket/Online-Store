package com.store.service;

import com.store.model.Product;
import com.store.model.ProductInBasket;
import com.store.repos.ProductInBasketRepo;
import com.store.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductInBasketRepo productInBasketRepo;

    public Page<Product> getProductsByCategories(String type, String season,
                                                 String gender, Pageable pageable) {
       return productRepo.findByTypeAndSeasonAndGender(type, season, gender, pageable);
    }

    public Product findById(Integer id) {
        return productRepo.findById(id);
    }

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public Page<Product> findByNameOrDescription(String text, String search, Pageable pageable ) {
        if (search.equals("name")) {
            return productRepo.findByNameIsContaining( text, pageable);
        } else if ( search.equals("description")) {
            return productRepo.findByDescriptionIsContaining( text, pageable );
        }
        return null;
    }

    public void deleteProduct(Integer id) {
        if(productRepo.findById(id) != null) {
            productRepo.deleteById(id);
        }
        productInBasketRepo.deleteByProductId(id);
    }

    public void editProduct(Integer id, String name, String price,
                            String count, String description,
                            String season, String type, String gender) {
        Product product = productRepo.findById(id);
        product.setName(name);
        product.setCount(Integer.parseInt(count));
        product.setGender(gender);
        product.setType(type);
        product.setSeason(season);
        product.setPrice(Integer.parseInt(price));
        product.setDescription(description);
        productRepo.save(product);
        List<ProductInBasket> productInBaskets = productInBasketRepo.findByProductId(id);
        for(ProductInBasket p : productInBaskets) {
            p.setProductName(name);
            p.setProductPrice(Integer.parseInt(price));
            p.setProductTotalPrice(p.getCount()*p.getProductPrice());
            productInBasketRepo.save(p);
        }
    }
}
