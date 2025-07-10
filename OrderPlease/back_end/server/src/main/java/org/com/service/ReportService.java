package org.com.service;

import org.com.vo.OrderReportVO;
import org.com.vo.SalesTop10ReportVO;
import org.com.vo.TurnoverReportVO;
import org.com.vo.UserReportVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface ReportService {
    SalesTop10ReportVO selectSalesTop10(LocalDate begin, LocalDate end);

    OrderReportVO ordersStatistics(LocalDate begin, LocalDate end);

    UserReportVO userStatistics(LocalDate begin, LocalDate end);

    TurnoverReportVO turnoverStatistics(LocalDate begin, LocalDate end);
}
