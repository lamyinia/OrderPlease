package org.com.service.impl;

import org.com.context.BaseContext;
import org.com.entity.ShoppingCart;
import org.com.mapper.ShoppingCartMapper;
import org.com.result.Result;
import org.com.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Override
    public List<ShoppingCart> showAllItem() {
        List<ShoppingCart> list = shoppingCartMapper.filter(ShoppingCart.builder()
                .userId(BaseContext.getCurrentId())
                .build());

        return list;
    }
}
