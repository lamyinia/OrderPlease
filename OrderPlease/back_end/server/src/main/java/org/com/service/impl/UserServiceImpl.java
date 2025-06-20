package org.com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.com.entity.User;
import org.com.mapper.UserMapper;
import org.com.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
