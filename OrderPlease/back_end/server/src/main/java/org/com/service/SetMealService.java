package org.com.service;

import org.com.dto.SetMealDTO;
import org.com.dto.SetMealPageQueryDTO;
import org.com.result.PageResult;
import org.springframework.stereotype.Service;

@Service
public interface SetMealService {
    void addSetMeal(SetMealDTO setMealDTO);

    PageResult pageQuery(SetMealPageQueryDTO setMealPageQueryDTO);
}
