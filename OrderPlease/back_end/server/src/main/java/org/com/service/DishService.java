package org.com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.com.dto.DishDTO;
import org.com.dto.DishPageQueryDTO;
import org.com.entity.Dish;
import org.com.result.PageResult;
import org.com.result.Result;
import org.com.vo.DishVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DishService extends IService<Dish> {
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);
    void addDish(DishDTO dishDTO);
    DishVO getByIdWithFlavor(Long id);
    List<DishVO> listWithFlavor(Dish dish);
}
