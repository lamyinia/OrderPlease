package org.com.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.com.context.BaseContext;
import org.com.entity.ShoppingCart;
import org.com.result.Result;
import org.com.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("user/shoppingCart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;


    @PostMapping("/add")
    public void addItem(){

    }

    @GetMapping("/list")
    public Result<List<ShoppingCart>> selectList(){
        List<ShoppingCart> list = shoppingCartService.showAllItem();
        return Result.success(list);
    }
}
