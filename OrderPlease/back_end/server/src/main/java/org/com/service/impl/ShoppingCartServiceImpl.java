package org.com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.com.context.BaseContext;
import org.com.dto.ShoppingCartDTO;
import org.com.entity.Dish;
import org.com.entity.SetMeal;
import org.com.entity.ShoppingCart;
import org.com.mapper.DishMapper;
import org.com.mapper.SetMealMapper;
import org.com.mapper.ShoppingCartMapper;
import org.com.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Autowired
    ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetMealMapper setMealMapper;

    @Override
    public List<ShoppingCart> showAllItems() {
        List<ShoppingCart> list = shoppingCartMapper.filter(ShoppingCart.builder()
                .userId(BaseContext.getCurrentId())
                .build());

        return list;
    }

    @Override
    public void addItem(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart item = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, item);

        Long userId = BaseContext.getCurrentId();
        item.setUserId(userId);

        List<ShoppingCart> lists = shoppingCartMapper.filter(item);

        if (lists != null && lists.size() > 0){
            ShoppingCart metaItem = lists.get(0);
            metaItem.setNumber(metaItem.getNumber() + 1);

            this.updateById(metaItem);
        } else {
            Long dishId = item.getDishId();
            if (dishId != null){
                Dish dish = dishMapper.selectById(dishId);
                item.setName(dish.getName());
                item.setImage(dish.getImage());
                item.setAmount(dish.getPrice());
            } else {
                Long setmealId = item.getSetmealId();
                SetMeal setMeal = setMealMapper.selectById(setmealId);
                item.setName(setMeal.getName());
                item.setImage(setMeal.getImage());
                item.setAmount(setMeal.getPrice());
            }

            item.setNumber(1);

            shoppingCartMapper.insert(item);
        }
    }

    @Override
    public void subItem(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart item = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, item);
        item.setUserId(BaseContext.getCurrentId());

        List<ShoppingCart> lists = shoppingCartMapper.filter(item);
        if (lists != null && lists.size() > 0){
            ShoppingCart metaItem = lists.get(0);
            if (metaItem.getNumber() <= 1){
                shoppingCartMapper.deleteById(metaItem.getId());
            } else {
                metaItem.setNumber(metaItem.getNumber() - 1);
                shoppingCartMapper.updateById(metaItem);
            }
        }
    }

    @Override
    public void cleanItems() {
        shoppingCartMapper.delete(new LambdaQueryWrapper<ShoppingCart>()
                .eq(ShoppingCart::getUserId, BaseContext.getCurrentId()));
    }
}
