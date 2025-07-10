package org.com.controller.user;

import lombok.RequiredArgsConstructor;
import org.com.constant.StatusConstant;
import org.com.entity.SetMeal;
import org.com.result.Result;
import org.com.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userSetMealController")
@RequestMapping("/user/setmeal")
@RequiredArgsConstructor
public class SetMealController {
    private final SetMealService setMealService;

    @GetMapping("/list")
    Result<List<SetMeal>> selectList(Long categoryId){
        List<SetMeal> list = setMealService.filter(SetMeal.builder()
                .categoryId(categoryId)
                .status(StatusConstant.ENABLE)
                .build());

        return Result.success(list);
    }
}
