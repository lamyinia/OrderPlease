package org.com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.com.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

    List<ShoppingCart> filter(ShoppingCart build);
}
