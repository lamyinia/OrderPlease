package org.com.service;

import org.com.vo.SalesTop10ReportVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface ReportService {
    SalesTop10ReportVO selectSalesTop10(LocalDate begin, LocalDate end);
}
