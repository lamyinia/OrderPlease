package org.com.controller.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.com.entity.Category;
import org.com.result.Result;
import org.com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userCategoryController")
@RequestMapping("/user/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/list")
    @ApiOperation("类型分页")
    public Result<List<Category>> selectByType(Integer type){
        List<Category> list = categoryService.selectByType(type);
        return Result.success(list);
    }
}
