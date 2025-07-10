package org.com.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.dto.CategoryDTO;
import org.com.dto.CategoryPageQueryDTO;
import org.com.entity.Category;
import org.com.result.PageResult;
import org.com.result.Result;
import org.com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "菜品套餐分类相关接口")
@Slf4j
@RestController("adminCategoryController")
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    // TODO
    @GetMapping("/list")
    @ApiOperation("类型分页")
    public Result<List<Category>> selectByType(Integer type){
        List<Category> list = categoryService.selectByType(type);
        return Result.success(list);
    }

    @GetMapping("/page")
    @ApiOperation("普通分页")
    public Result<PageResult> selectPage(CategoryPageQueryDTO categoryPageQueryDTO){
        return Result.success(categoryService.selectPage(categoryPageQueryDTO));
    }

    @PostMapping
    @ApiOperation("新增")
    public Result<String> addCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("根据键值删除")
    public Result<String> deleteById(Long id){
        categoryService.removeById(id);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("改变状态")
    public Result<String> turnState(@PathVariable("status") Integer status, Long id){
        categoryService.turnState(status, id);
        return Result.success();
    }
}
