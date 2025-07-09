package org.com.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.com.dto.OrdersSubmitDTO;
import org.com.result.Result;
import org.com.service.OrderService;
import org.com.vo.OrderSubmitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("userOrderController")
@RequestMapping("/user/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
        log.info("用户下单，参数是: {}", ordersSubmitDTO);
        OrderSubmitVO result = orderService.submitOrder(ordersSubmitDTO);
        return Result.success(result);
    }
}
