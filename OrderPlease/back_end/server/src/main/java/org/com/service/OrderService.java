package org.com.service;

import org.com.dto.OrdersSubmitDTO;
import org.com.vo.OrderSubmitVO;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);
}
