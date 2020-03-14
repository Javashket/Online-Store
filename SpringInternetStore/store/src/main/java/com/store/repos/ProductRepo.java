package com.store.repos;

import com.store.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProductRepo extends CrudRepository <Product,Long> {

    Page<Product> findByTypeAndSeasonAndGender(String type, String season,
                                               String gender ,Pageable pageable);

    Page<Product> findByNameIsContaining(String name, Pageable pageable);

    Page<Product> findByDescriptionIsContaining(String description, Pageable pageable);

    void deleteById(Integer id);

    Product findById(Integer id);



}
