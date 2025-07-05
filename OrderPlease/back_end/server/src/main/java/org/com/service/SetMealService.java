package org.com.service;

import org.com.dto.SetMealDTO;
import org.springframework.stereotype.Service;

@Service
public interface SetMealService {
    void addSetMeal(SetMealDTO setMealDTO);
}
