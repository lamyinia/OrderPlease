package org.com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.com.dto.CategoryPageQueryDTO;
import org.com.entity.Category;

public interface CategoryMapper extends BaseMapper<Category> {
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);
}
