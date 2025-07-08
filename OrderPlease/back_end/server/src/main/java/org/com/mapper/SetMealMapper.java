package org.com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.com.annotation.AutoFillSetter;
import org.com.dto.SetMealPageQueryDTO;
import org.com.entity.SetMeal;
import org.com.enumeration.OperationType;
import org.com.vo.SetMealVO;

import java.util.List;

public interface SetMealMapper extends BaseMapper<SetMeal> {
    Page<SetMealVO> pageQuery(SetMealPageQueryDTO setMealPageQueryDTO);

    @AutoFillSetter(value = OperationType.INSERT)
    void addSetMeal(SetMeal setMeal);

    List<SetMeal> filter(SetMeal build);
}
