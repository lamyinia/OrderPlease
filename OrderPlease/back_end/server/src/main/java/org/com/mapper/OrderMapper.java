package org.com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.com.dto.GoodsSalesDTO;
import org.com.entity.Orders;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderMapper extends BaseMapper<Orders> {
    List<GoodsSalesDTO> selectSalesTop10(LocalDateTime beginTime, LocalDateTime endTime);
}
