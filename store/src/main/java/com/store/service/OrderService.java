package com.store.service;

import com.store.model.Orderr;
import com.store.model.User;
import com.store.repos.OrderrRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {

    private final OrderrRepo orderrRepo;

    public List<Orderr> getAllOrders() {
        return orderrRepo.getAllBy();
    }

    public List<Orderr> getOrdersById(User user) {
        return orderrRepo.findByUser_Id(user.getId());
    }
}
