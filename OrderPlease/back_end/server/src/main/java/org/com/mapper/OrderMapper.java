package org.com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;
import org.com.dto.GoodsSalesDTO;
import org.com.dto.OrdersPageQueryDTO;
import org.com.entity.Orders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderMapper extends BaseMapper<Orders> {
    List<GoodsSalesDTO> selectSalesTop10(LocalDateTime beginTime, LocalDateTime endTime);

    @Select("select * from orders where number = #{orderNumber} and user_id= #{userId}")
    Orders getByNumberAndUserId(String orderNumber, Long userId);

    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);

    Double sumByMap(Map map);

    Integer countByMap(Map<String, Object> map);
}
