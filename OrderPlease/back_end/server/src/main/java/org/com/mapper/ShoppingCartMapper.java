package org.com.mapper;

import org.com.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartMapper {

    List<ShoppingCart> filter(ShoppingCart build);
}
