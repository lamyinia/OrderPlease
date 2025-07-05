package org.com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.com.annotation.AutoFillSetter;
import org.com.constant.StatusConstant;
import org.com.dto.CategoryDTO;
import org.com.dto.CategoryPageQueryDTO;
import org.com.entity.Category;
import org.com.enumeration.OperationType;
import org.com.mapper.CategoryMapper;
import org.com.result.PageResult;
import org.com.service.CategoryService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public PageResult selectPage(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> result = categoryMapper.pageQuery(categoryPageQueryDTO);

        return new PageResult(result.getTotal(), result.getResult());
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

    @Override
    public void turnState(Integer status, Long id) {
        Category category = Category.builder()
                .id(id)
                .status(status)
                .build();

        this.updateById(category);
    }

    @Override
    public List<Category> selectByType(Integer type) {
        return this.list(new LambdaQueryWrapper<Category>()
                .eq(Category::getType, type));
    }
}
