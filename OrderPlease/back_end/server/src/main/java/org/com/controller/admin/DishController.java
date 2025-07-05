package org.com.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.com.dto.DishDTO;
import org.com.dto.DishPageQueryDTO;
import org.com.entity.Dish;
import org.com.result.PageResult;
import org.com.result.Result;
import org.com.service.DishService;
import org.com.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "菜品相关接口")
@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping("/page")
    @ApiOperation("普通分页")
    public Result<PageResult> selectPage(DishPageQueryDTO dishPageQueryDTO){
        return dishService.selectPage(dishPageQueryDTO);
    }

    @PostMapping()
    @ApiOperation("新增")
    public Result<String> addDish(@RequestBody DishDTO dishDTO){
        dishService.addDish(dishDTO);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("类型分页")
    public Result<List<Dish>> selectByType(Long categoryId){
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dish::getCategoryId, categoryId);

        return Result.success(dishService.list(wrapper));
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getByIdWithFlavor(@PathVariable Long id) {
        log.info("根据id查询菜品：{}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }
}
