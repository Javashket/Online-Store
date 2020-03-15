package com.store.repos;

import com.store.model.ProductInOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProductInOrderRepo extends CrudRepository <ProductInOrder,Long> {

    ProductInOrder findById(Integer id);

    void removeById(Integer productId);
}
