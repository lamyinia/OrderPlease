package org.com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.com.annotation.AutoFillSetter;
import org.com.dto.DishPageQueryDTO;
import org.com.entity.Dish;
import org.com.enumeration.OperationType;
import org.com.vo.DishVO;

public interface DishMapper extends BaseMapper<Dish> {
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    @AutoFillSetter(value = OperationType.INSERT)
    void insertDish(Dish dish);
}
