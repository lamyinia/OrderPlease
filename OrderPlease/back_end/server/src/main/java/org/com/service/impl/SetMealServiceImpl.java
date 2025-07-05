package org.com.service.impl;

import com.github.pagehelper.PageHelper;
import org.com.dto.SetMealDTO;
import org.com.dto.SetMealPageQueryDTO;
import org.com.entity.SetMeal;
import org.com.entity.SetMealDish;
import org.com.mapper.DishMapper;
import org.com.mapper.SetMealDishMapper;
import org.com.mapper.SetMealMapper;
import org.com.result.PageResult;
import org.com.service.SetMealService;
import org.com.vo.SetMealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealMapper setMealMapper;
    @Autowired
    private SetMealDishMapper setMealDishMapper;
    @Autowired
    private DishMapper dishMapper;

    @Override
    @Transactional
    public void addSetMeal(SetMealDTO setMealDTO) {
        SetMeal setMeal = new SetMeal();
        BeanUtils.copyProperties(setMealDTO, setMeal);

        setMealMapper.addSetMeal(setMeal);

        Long id = setMeal.getId();
        List<SetMealDish> setMealDishes = setMealDTO.getSetMealDishes();
        setMealDishes.forEach(setMealDish -> {
            setMealDish.setSetmealId(id);
        });

        setMealDishMapper.insertBatch(setMealDishes);
    }

    @Override
    public PageResult pageQuery(SetMealPageQueryDTO setMealPageQueryDTO) {
        PageHelper.startPage(setMealPageQueryDTO.getPage(), setMealPageQueryDTO.getPageSize());

        List<SetMealVO> result = setMealMapper.pageQuery(setMealPageQueryDTO).getResult();
        return new PageResult(result.size(), result);
    }
}
