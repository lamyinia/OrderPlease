package org.com.service;

import org.com.dto.OrdersPageQueryDTO;
import org.com.dto.OrdersPaymentDTO;
import org.com.dto.OrdersSubmitDTO;
import org.com.result.PageResult;
import org.com.vo.OrderPaymentVO;
import org.com.vo.OrderSubmitVO;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO);

    void paySuccess(String orderNumber);

    PageResult pageQueryOrder(int page, int pageSize, Integer status);

    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);
}
