package org.com.service.impl;

import org.com.dto.SetMealDTO;
import org.com.mapper.SetMealMapper;
import org.com.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    SetMealMapper setMealMapper;

    @Override
    public void addSetMeal(SetMealDTO setMealDTO) {

    }
}
