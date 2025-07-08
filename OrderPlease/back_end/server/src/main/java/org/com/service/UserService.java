package org.com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.com.dto.UserLoginDTO;
import org.com.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {
    User login(UserLoginDTO userLoginDTO);
}
