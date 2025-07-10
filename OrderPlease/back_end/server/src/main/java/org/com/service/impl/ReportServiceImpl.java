package org.com.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.com.dto.GoodsSalesDTO;
import org.com.mapper.OrderMapper;
import org.com.mapper.UserMapper;
import org.com.service.ReportService;
import org.com.vo.SalesTop10ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public SalesTop10ReportVO selectSalesTop10(LocalDate begin, LocalDate end) {
        LocalDateTime beginTime = null, endTime = null;
        if (begin != null){
            beginTime = LocalDateTime.of(begin, LocalTime.MIN);
        }
        if (end != null){
            endTime = LocalDateTime.of(end, LocalTime.MAX);
        }

        List<GoodsSalesDTO> salesTop10 = orderMapper.selectSalesTop10(beginTime, endTime);

        List<String> names = salesTop10.stream().map(GoodsSalesDTO::getName).collect(Collectors.toList());
        String nameList = StringUtils.join(names, ",");

        List<Integer> numbers = salesTop10.stream().map(GoodsSalesDTO::getNumber).collect(Collectors.toList());
        String numberList = StringUtils.join(numbers, ",");

        return SalesTop10ReportVO.builder()
                .nameList(nameList)
                .numberList(numberList)
                .build();
    }
}
