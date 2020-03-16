package com.store.service;

import com.store.model.ListDesires;
import com.store.model.Product;
import com.store.model.User;
import com.store.repos.ListDesiresRepo;
import com.store.repos.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
@Value
@NonFinal
public class ListDesireService {

    ProductRepo productRepo;

    ListDesiresRepo listDesiresRepo;

    public void addProduct(User user, Integer id) {
        Set<Product> productsInListDesires = listDesiresRepo.findById(user.getListDesires().getId()).getProducts();
        boolean contain_result = true;
        for (Product p : productsInListDesires) {
            if (p.getId().equals(id)) {
                contain_result = false;
                break;
            }
        }
        if (contain_result) {
            ListDesires listDesires = listDesiresRepo.findById(user.getListDesires().getId());
            Product product = productRepo.findById(id);
            product.addListDesires(listDesires);
            listDesires.addProducts(product);
            productRepo.save(product);
            listDesiresRepo.save(listDesires);
        }
    }

    public void deleteProduct(User user, Integer id) {
        ListDesires listDesires = listDesiresRepo.findById(user.getListDesires().getId());
        Product product = productRepo.findById(id);
        listDesires.deleteProduct(product);
        product.removeListDesire(listDesires);
        productRepo.save(product);
        Set<ListDesires> listDesires1 = listDesiresRepo.findAllBy();
        for (ListDesires p : listDesires1) {
            p.deleteProduct(product);
            listDesiresRepo.save(p);
        }
    }

    public ListDesires findById(Integer id) {
        return listDesiresRepo.findById(id);
    }
}
