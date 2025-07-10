package org.com.service;

import org.com.vo.BusinessDataVO;
import org.com.vo.DishOverViewVO;
import org.com.vo.OrderOverViewVO;
import org.com.vo.SetMealOverViewVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface WorkSpaceService {
    SetMealOverViewVO getOverViewSetMeals();

    DishOverViewVO getOverViewDishes();

    OrderOverViewVO getOverViewOrders();

    BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end);
}
