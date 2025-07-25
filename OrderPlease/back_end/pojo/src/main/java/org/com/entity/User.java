package org.com.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String openId;

    private String name;

    private String phone;

    private String sex;

    private String identityCard;

    private String avatar;

    private LocalDateTime createTime;
}

