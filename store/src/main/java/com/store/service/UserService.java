package com.store.service;

import com.store.model.Basket;
import com.store.model.ListDesires;
import com.store.model.User;
import com.store.repos.*;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Value
@NonFinal
@AllArgsConstructor
public class UserService implements UserDetailsService {

    UserRepo userRepo;

    MailSender mailSender;

    PasswordEncoder passwordEncoder;

    ListDesiresRepo listDesiresRepo;

    BasketRepo basketRepo;

    ProductInBasketRepo productInBasketRepo;

    OrderrRepo orderrRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepo.findByLogin(username);
    }

    public boolean deleteUserById(Integer id) {
        User user = userRepo.findById(id);
        if (user != null) {
            userRepo.deleteById(id);
            listDesiresRepo.deleteById(user.getListDesires().getId());
            Integer basketId = user.getBasket().getId();
            basketRepo.deleteById(basketId);
            productInBasketRepo.deleteByBasket_Id(basketId);
            orderrRepo.deleteByUser_Id(user.getId());
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userRepo.getAllBy();
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByEmail(user.getEmail());
        if (userFromDb != null) {
            return false;
        }
        user.setActivationCode(UUID.randomUUID().toString());
        if (user.getRole().equals("CUSTOMER")) {
            user.setBalance(1000);
            ListDesires listDesires = new ListDesires();
            Basket basket = new Basket();
            user.setBasket(basket);
            basket.setUser(user);
            user.setListDesires(listDesires);
            listDesires.setUser(user);
        } else {
            user.setBalance(null);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        if (user.getEmail() != null) {
            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            "Добро пожаловать в Online Clothing Store.\n" +
                            "Пожалуйста перейдите по ссылке ниже для активации аккаунта.\n" +
                            "http://localhost:8080/activate/%s",
                    user.getLogin(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return true;
    }

    public boolean recoveryUser(String email) {
        User userFromDb = userRepo.findByEmail(email);
        if (userFromDb == null) {
            return false;
        }
        userFromDb.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(userFromDb);
        if (userFromDb.getEmail() != null) {
            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            "Для восстановления пароля в Online Clothing Store,\n" +
                            "пожалуйста перейдите по ссылке ниже.\n" +
                            "http://localhost:8080/recovery_pass/%s",
                    userFromDb.getLogin(),
                    userFromDb.getActivationCode()
            );
            mailSender.send(userFromDb.getEmail(), "Recovery password", message);
        }
        return true;
    }

    public User searchByActivationCode(String code) {
        return userRepo.findByActivationCode(code);
    }

    public void editUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }

    public User getUserById(Integer id) {
        return userRepo.findById(id);
    }

    public void editUserPasswordAndLogin(User user, String password,
                                         String login) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLogin(login);
        userRepo.save(user);
    }

    public void editUserPasswordAndLoginAndBalance(User user, String password,
                                                   String login, Integer balance) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLogin(login);
        user.setBalance(balance);
        userRepo.save(user);
    }
}
