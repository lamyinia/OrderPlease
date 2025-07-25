package org.com.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.constant.StatusConstant;
import org.com.dto.DishDTO;
import org.com.dto.DishPageQueryDTO;
import org.com.entity.Dish;
import org.com.result.PageResult;
import org.com.result.Result;
import org.com.service.DishService;
import org.com.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Api(tags = "菜品相关接口")
@Slf4j
@RestController("adminDishController")
@RequestMapping("/admin/dish")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;
    private final RedisTemplate redisTemplate;

    @GetMapping("/page")
    @ApiOperation("普通分页")
    public Result<PageResult> selectPage(DishPageQueryDTO dishPageQueryDTO){
        String key = "admin_page_" + String.valueOf(dishPageQueryDTO.getPage()) + "_" + String.valueOf(dishPageQueryDTO.getPageSize());

        PageResult cache = (PageResult)redisTemplate.opsForValue().get(key);

        if (cache != null){
            return Result.success(cache);
        }

        PageResult result = dishService.pageQuery(dishPageQueryDTO);
        redisTemplate.opsForValue().set(key, (PageResult) result);

        return Result.success(result);
    }

    @PostMapping()
    @ApiOperation("新增")
    public Result<String> addDish(@RequestBody DishDTO dishDTO){
        dishService.addDish(dishDTO);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("类型分页")
    public Result<List<DishVO>> selectByType(Long categoryId){
        Dish condition = Dish.builder().categoryId(categoryId).status(StatusConstant.ENABLE).build();
        List<DishVO> dishVOs = dishService.listWithFlavor(condition);

        return Result.success(dishVOs);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getByIdWithFlavor(@PathVariable Long id) {
        log.info("根据id查询菜品：{}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}
