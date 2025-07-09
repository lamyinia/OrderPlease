package org.com.controller.admin;

import org.com.result.Result;
import org.com.service.WorkSpaceService;
import org.com.vo.DishOverViewVO;
import org.com.vo.OrderOverViewVO;
import org.com.vo.SetMealOverViewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/workspace")
public class WorkSpaceController {
    @Autowired
    WorkSpaceService workSpaceService;

    @GetMapping("/overviewOrders")
    public Result<OrderOverViewVO> overViewOrders(){
        return Result.success(workSpaceService.getOverViewOrders());
    }

    @GetMapping("/overviewDishes")
    public Result<DishOverViewVO> overViewDishes(){
        return Result.success(workSpaceService.getOverViewDishes());
    }

    @GetMapping("/overviewSetmeals")
    public Result<SetMealOverViewVO> overViewSetMeals(){
        return Result.success(workSpaceService.getOverViewSetMeals());
    }
}
