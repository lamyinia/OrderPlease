package org.com.service;

import org.com.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingCartService {
    List<ShoppingCart> showAllItem();
}
