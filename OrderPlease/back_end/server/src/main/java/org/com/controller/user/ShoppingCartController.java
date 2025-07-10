package org.com.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.dto.ShoppingCartDTO;
import org.com.entity.ShoppingCart;
import org.com.result.Result;
import org.com.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("user/shoppingCart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public Result addItem(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("购物车商品添加操作");
        shoppingCartService.addItem(shoppingCartDTO);

        return Result.success();
    }

    @PostMapping("/sub")
    public Result subitem(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("购物车商品减少操作");
        shoppingCartService.subItem(shoppingCartDTO);

        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<ShoppingCart>> selectList(){
        List<ShoppingCart> list = shoppingCartService.showAllItems();
        return Result.success(list);
    }

    @DeleteMapping("/clean")
    public Result cleanItems(){
        shoppingCartService.cleanItems();
        return Result.success();
    }
}
