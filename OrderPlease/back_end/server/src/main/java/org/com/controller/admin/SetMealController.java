package org.com.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.com.dto.SetMealDTO;
import org.com.dto.SetMealPageQueryDTO;
import org.com.result.PageResult;
import org.com.result.Result;
import org.com.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "套餐相关接口")
@Slf4j
@RestController
@RequestMapping("/admin/setmeal")
public class SetMealController {
    @Autowired
    SetMealService setMealService;

    @PostMapping
    @ApiOperation("新增套餐")
    public Result addSetMeal(@RequestBody SetMealDTO setMealDTO){
        setMealService.addSetMeal(setMealDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    public Result<PageResult> page(SetMealPageQueryDTO setMealPageQueryDTO){
        PageResult pageResult = setMealService.pageQuery(setMealPageQueryDTO);
        return Result.success(pageResult);
    }
}
