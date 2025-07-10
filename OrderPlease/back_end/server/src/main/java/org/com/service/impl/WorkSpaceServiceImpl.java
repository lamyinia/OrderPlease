package org.com.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.constant.StatusConstant;
import org.com.entity.Orders;
import org.com.mapper.DishMapper;
import org.com.mapper.OrderMapper;
import org.com.mapper.SetMealMapper;
import org.com.mapper.UserMapper;
import org.com.service.WorkSpaceService;
import org.com.vo.BusinessDataVO;
import org.com.vo.DishOverViewVO;
import org.com.vo.OrderOverViewVO;
import org.com.vo.SetMealOverViewVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkSpaceServiceImpl implements WorkSpaceService {
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;
    private final DishMapper dishMapper;
    private final SetMealMapper setMealMapper;

    @Override
    public SetMealOverViewVO getOverViewSetMeals() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", StatusConstant.ENABLE);
        Integer sold = setMealMapper.countByMap(map);

        map.put("status", StatusConstant.DISABLE);
        Integer discontinued = setMealMapper.countByMap(map);

        return SetMealOverViewVO.builder()
                .sold(sold)
                .discontinued(discontinued)
                .build();
    }

    @Override
    public DishOverViewVO getOverViewDishes() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", StatusConstant.ENABLE);
        Integer sold = dishMapper.countByMap(map);

        map.put("status", StatusConstant.DISABLE);
        Integer discontinued = dishMapper.countByMap(map);

        return DishOverViewVO.builder()
                .sold(sold)
                .discontinued(discontinued)
                .build();
    }

    @Override
    public OrderOverViewVO getOverViewOrders() {
        Map<String, Object> map = new HashMap<>();
        map.put("begin", LocalDateTime.now().with(LocalTime.MIN));
        map.put("status", Orders.TO_BE_CONFIRMED);

        Integer waitingOrders = orderMapper.countByMap(map);

        map.put("status", Orders.CONFIRMED);
        Integer deliveredOrders = orderMapper.countByMap(map);

        map.put("status", Orders.COMPLETED);
        Integer completedOrders = orderMapper.countByMap(map);

        map.put("status", Orders.CANCELLED);
        Integer cancelledOrders = orderMapper.countByMap(map);

        map.put("status", null);
        Integer allOrders = orderMapper.countByMap(map);

        return OrderOverViewVO.builder()
                .waitingOrders(waitingOrders)
                .deliveredOrders(deliveredOrders)
                .completedOrders(completedOrders)
                .cancelledOrders(cancelledOrders)
                .allOrders(allOrders)
                .build();
    }

    @Override
    public BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end) {
        Map<String, Object> map = new HashMap<>();
        map.put("begin",begin);
        map.put("end",end);

        Integer totalOrderCount = orderMapper.countByMap(map);

        map.put("status", Orders.COMPLETED);
        Integer validOrderCount = orderMapper.countByMap(map);
        Double turnover = orderMapper.sumByMap(map);
        if (turnover == null) turnover = 0.0;

        Double unitPrice = 0.0, orderCompletionRate = 0.0;

        if(totalOrderCount != 0 && validOrderCount != 0){
            orderCompletionRate = validOrderCount.doubleValue() / totalOrderCount;
            unitPrice = turnover / validOrderCount;
        }

        Integer newUsers = userMapper.countByMap(map);

        return BusinessDataVO.builder()
                .turnover(turnover)
                .validOrderCount(validOrderCount)
                .orderCompletionRate(orderCompletionRate)
                .unitPrice(unitPrice)
                .newUsers(newUsers)
                .build();
    }
}
