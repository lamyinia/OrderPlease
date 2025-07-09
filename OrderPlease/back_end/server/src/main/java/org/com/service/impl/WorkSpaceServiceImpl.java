package org.com.service.impl;

import org.com.mapper.DishMapper;
import org.com.mapper.OrderMapper;
import org.com.mapper.SetMealMapper;
import org.com.mapper.UserMapper;
import org.com.service.WorkSpaceService;
import org.com.vo.DishOverViewVO;
import org.com.vo.OrderOverViewVO;
import org.com.vo.SetMealOverViewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkSpaceServiceImpl implements WorkSpaceService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetMealMapper setMealMapper;

    @Override
    public SetMealOverViewVO getOverViewSetMeals() {
        return null;
    }

    @Override
    public DishOverViewVO getOverViewDishes() {
        return null;
    }

    @Override
    public OrderOverViewVO getOverViewOrders() {
        return null;
    }
}
