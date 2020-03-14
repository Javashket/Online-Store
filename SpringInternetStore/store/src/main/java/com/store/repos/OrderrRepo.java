package com.store.repos;

import com.store.model.Orderr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderrRepo extends CrudRepository<Orderr, Long> {

    List<Orderr> getAllBy();

    List<Orderr> findByUser_Id(Integer id);

    void deleteByUser_Id(Integer id);

}
