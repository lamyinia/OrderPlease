package org.com.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OrdersPageQueryDTO implements Serializable {
    private int page;

    private int pageSize;

    private String number;

    private  String phone;

    private Integer status;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private Long userId;
}
