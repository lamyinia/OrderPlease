package org.com.service;

import org.com.vo.DishOverViewVO;
import org.com.vo.OrderOverViewVO;
import org.com.vo.SetMealOverViewVO;
import org.springframework.stereotype.Service;

@Service
public interface WorkSpaceService {
    SetMealOverViewVO getOverViewSetMeals();

    DishOverViewVO getOverViewDishes();

    OrderOverViewVO getOverViewOrders();
}
