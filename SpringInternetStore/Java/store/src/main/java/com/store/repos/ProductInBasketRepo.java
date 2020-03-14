package com.store.repos;

import com.store.model.ProductInBasket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductInBasketRepo extends CrudRepository <ProductInBasket,Long> {

    ProductInBasket findById ( Integer id);

    List <ProductInBasket> findByBasket_Id( Integer id);

    List <ProductInBasket> findByProductId ( Integer id);

    void deleteByProductId (Integer id);

    void removeById(Integer productId);

    void deleteByBasket_Id( Integer id);
}
