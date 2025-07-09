package org.com.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.com.result.Result;
import org.com.service.ReportService;
import org.com.vo.SalesTop10ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/admin/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping("/top10")
    public Result<SalesTop10ReportVO> selectSalesTop10(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
            ){
        log.info("销量前10的商品在 {} 到 {} 期间", begin, end);

        return Result.success(reportService.selectSalesTop10(begin, end));
    }
}
