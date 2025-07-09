package org.com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.com.constant.MessageConstant;
import org.com.context.BaseContext;
import org.com.dto.OrdersSubmitDTO;
import org.com.entity.AddressBook;
import org.com.entity.OrderDetail;
import org.com.entity.Orders;
import org.com.entity.ShoppingCart;
import org.com.exception.AddressBookBusinessException;
import org.com.exception.ShoppingCarBusinessException;
import org.com.mapper.AddressBookMapper;
import org.com.mapper.OrderDetailMapper;
import org.com.mapper.OrderMapper;
import org.com.mapper.ShoppingCartMapper;
import org.com.service.AddressBookService;
import org.com.service.OrderService;
import org.com.vo.OrderSubmitVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private AddressBookMapper addressBookMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO) {
        AddressBook byId = addressBookMapper.selectById(ordersSubmitDTO.getAddressBookId());
        if (byId == null){
            throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
        }

        Long userId = BaseContext.getCurrentId();
        List<ShoppingCart> cartList = shoppingCartMapper.filter(ShoppingCart.builder().userId(userId).build());
        if (cartList == null || cartList.size() == 0){
            throw new ShoppingCarBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
        }

        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersSubmitDTO, orders);
        orders.setPayStatus(Orders.UN_PAID);
        orders.setPayStatus(Orders.PENDING_PAYMENT);
        orders.setNumber(String.valueOf(LocalDateTime.now()));
        orders.setAddress(byId.getDetail());
        orders.setPhone(byId.getPhone());
        orders.setConsignee(byId.getConsignee());
        orders.setUserId(userId);

        orderMapper.insert(orders);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (ShoppingCart item : cartList) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(item, orderDetail);
            orderDetail.setOrderId(orders.getId());

            orderDetailList.add(orderDetail);
        }

        orderDetailMapper.insert(orderDetailList);

        shoppingCartMapper.delete(new LambdaQueryWrapper<ShoppingCart>().eq(ShoppingCart::getUserId, userId));

        OrderSubmitVO result = OrderSubmitVO.builder()
                .id(orders.getId())
                .orderTime(orders.getOrderTime())
                .orderNumber(orders.getNumber())
                .orderAmount(orders.getAmount())
                .build();

        return result;
    }
}
