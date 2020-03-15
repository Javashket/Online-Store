package com.store.repos;

import com.store.model.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BasketRepo extends CrudRepository<Basket, Long> {

    Basket findById (Integer id);

    void deleteById( Integer id);
}
