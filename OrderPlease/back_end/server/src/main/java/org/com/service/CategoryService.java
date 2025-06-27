package org.com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.com.dto.CategoryDTO;
import org.com.dto.CategoryPageQueryDTO;
import org.com.entity.Category;
import org.com.result.PageResult;
import org.com.result.Result;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService extends IService<Category> {
    Result<PageResult> selectPage(CategoryPageQueryDTO categoryPageQueryDTO);

    void addCategory(CategoryDTO categoryDTO);
    void addCategory(Category category);
    void updateCategory(CategoryDTO categoryDTO);
}
