package org.com.dto;

import lombok.Data;
import org.com.entity.SetMealDish;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class SetMealDTO implements Serializable {
    private Long id;

    private Long categoryId;

    private String name;

    private BigDecimal price;

    //状态 0:停用 1:启用
    private Integer status;

    private String description;

    private String image;

    private List<SetMealDish> setMealDishes = new ArrayList<>();
}

