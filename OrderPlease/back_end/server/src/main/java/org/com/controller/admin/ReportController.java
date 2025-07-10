package org.com.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.result.Result;
import org.com.service.ReportService;
import org.com.vo.OrderReportVO;
import org.com.vo.SalesTop10ReportVO;
import org.com.vo.TurnoverReportVO;
import org.com.vo.UserReportVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/admin/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/top10")
    public Result<SalesTop10ReportVO> selectSalesTop10(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
            ){
        log.info("销量前10的商品在 {} 到 {} 期间", begin, end);

        end = null;

        return Result.success(reportService.selectSalesTop10(begin, end));
    }

    @GetMapping("/turnoverStatistics")
    public Result<TurnoverReportVO> turnoverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("营业额数据统计：{},{}",begin, end);

        TurnoverReportVO result = reportService.turnoverStatistics(begin, end);
        return Result.success(result);
    }

    @GetMapping("/userStatistics")
    public Result<UserReportVO> userStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("用户数据统计：{},{}", begin, end);

        UserReportVO result = reportService.userStatistics(begin, end);
        return Result.success(result);
    }

    @GetMapping("/ordersStatistics")
    public Result<OrderReportVO> ordersStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("订单数据统计：{},{}", begin, end);

        OrderReportVO result = reportService.ordersStatistics(begin, end);
        return Result.success(result);
    }

}
