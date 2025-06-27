package org.com.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.com.dto.CategoryDTO;
import org.com.dto.CategoryPageQueryDTO;
import org.com.result.PageResult;
import org.com.result.Result;
import org.com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("菜品套餐分类接口")
@Slf4j
@RestController
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ApiOperation("新增")
    public Result<String> addCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    public Result<PageResult> selectPage(CategoryPageQueryDTO categoryPageQueryDTO){
        return categoryService.selectPage(categoryPageQueryDTO);
    }

    @PutMapping
    @ApiOperation("修改")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("根据键值删除")
    public Result<String> deleteById(Long id){
        categoryService.removeById(id);
        return Result.success();
    }

    // TODO
    public Result<String> turnState(){
        return null;
    }
    public Result<String> selectByType(){
        return null;
    }
}
