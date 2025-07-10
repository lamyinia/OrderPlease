package org.com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.com.entity.User;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    Integer countByMap(Map map);
}
