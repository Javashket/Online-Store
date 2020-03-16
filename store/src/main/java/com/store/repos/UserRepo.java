package com.store.repos;

import com.store.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepo extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findById(Integer id);

    User findByLogin(String login);

    User findByActivationCode(String code);

    List<User> getAllBy();

    void deleteById(Integer id);

}
