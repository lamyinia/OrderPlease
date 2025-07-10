package org.com.service;

import org.com.dto.*;
import org.com.result.PageResult;
import org.com.vo.OrderPaymentVO;
import org.com.vo.OrderStatisticsVO;
import org.com.vo.OrderSubmitVO;
import org.com.vo.OrderVO;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO);

    void paySuccess(String orderNumber);

    PageResult pageQueryOrder(int page, int pageSize, Integer status);

    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    OrderVO details(Long id);

    void delivery(Long id);

    void complete(Long id);

    void cancel(OrdersCancelDTO ordersCancelDTO);

    OrderStatisticsVO statistics();

    void reject(OrdersRejectionDTO ordersRejectionDTO);

    void confirm(OrdersConfirmDTO ordersConfirmDTO);
}
