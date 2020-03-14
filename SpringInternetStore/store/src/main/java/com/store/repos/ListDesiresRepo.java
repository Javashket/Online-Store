package com.store.repos;

import com.store.model.ListDesires;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
@Transactional
public interface ListDesiresRepo extends CrudRepository<ListDesires, Long> {

    ListDesires findById( Integer id);

    Set<ListDesires> findAllBy();

    void deleteById(Integer id);
}
