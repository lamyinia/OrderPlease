package org.com.mapper;

import org.com.dto.GoodsSalesDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderMapper {
    List<GoodsSalesDTO> selectSalesTop10(LocalDateTime beginTime, LocalDateTime endTime);
}
