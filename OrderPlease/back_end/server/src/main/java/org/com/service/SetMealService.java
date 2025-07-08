package org.com.service;

import org.com.dto.SetMealDTO;
import org.com.dto.SetMealPageQueryDTO;
import org.com.entity.SetMeal;
import org.com.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SetMealService {
    void addSetMeal(SetMealDTO setMealDTO);

    PageResult pageQuery(SetMealPageQueryDTO setMealPageQueryDTO);

    List<SetMeal> filter(SetMeal build);
}
