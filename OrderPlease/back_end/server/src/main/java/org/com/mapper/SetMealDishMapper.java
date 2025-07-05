package org.com.mapper;

import org.com.entity.SetMealDish;

import java.util.List;

public interface SetMealDishMapper {
    void insertBatch(List<SetMealDish> setMealDishes);
}
