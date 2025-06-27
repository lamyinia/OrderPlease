package org.com.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.com.annotation.AutoFillSetter;
import org.com.constant.StatusConstant;
import org.com.dto.CategoryDTO;
import org.com.dto.CategoryPageQueryDTO;
import org.com.entity.Category;
import org.com.enumeration.OperationType;
import org.com.mapper.CategoryMapper;
import org.com.result.PageResult;
import org.com.result.Result;
import org.com.service.CategoryService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public Result<PageResult> selectPage(CategoryPageQueryDTO categoryPageQueryDTO) {
        Page<Category> page = this.page(new Page<>(categoryPageQueryDTO.getPageNum(), categoryPageQueryDTO.getPageSize()));
        return Result.success(new PageResult(page.getTotal(), page.getRecords()));
    }

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setStatus(StatusConstant.ENABLE);

        CategoryService proxy = (CategoryService) AopContext.currentProxy();
        proxy.addCategory(category);
    }

    @Override
    @AutoFillSetter(value = OperationType.INSERT)
    public void addCategory(Category category) {
        this.save(category);
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setStatus(StatusConstant.ENABLE);

        this.updateById(category);
    }
}
