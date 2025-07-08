package org.com.controller.user;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.com.constant.StatusConstant;
import org.com.entity.Dish;
import org.com.result.Result;
import org.com.service.DishService;
import org.com.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("userDishController")
@RequestMapping("/user/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @ApiOperation("查询某个分类的菜品")
    @GetMapping("/list")
    public Result<List<DishVO>> list(Long categoryId){
        Dish condition = Dish.builder().categoryId(categoryId).status(StatusConstant.ENABLE).build();
        List<DishVO> dishVOs = dishService.listWithFlavor(condition);

        return Result.success(dishVOs);
    }
}
