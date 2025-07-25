package org.com.service;

import org.com.dto.ShoppingCartDTO;
import org.com.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingCartService {
    List<ShoppingCart> showAllItems();

    void addItem(ShoppingCartDTO shoppingCartDTO);

    void subItem(ShoppingCartDTO shoppingCartDTO);

    void cleanItems();
}
