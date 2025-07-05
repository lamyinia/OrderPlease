package org.com.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.com.annotation.AutoFillSetter;
import org.com.entity.DishFlavor;
import org.com.enumeration.OperationType;

import java.util.List;

public interface DishFlavorMapper {
    void insertBatch(List<DishFlavor> flavors);

    @AutoFillSetter(value = OperationType.INSERT)
    void insertFlavor(DishFlavor dishFlavor);

    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
