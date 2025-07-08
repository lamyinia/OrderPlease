package org.com.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.com.dto.UserLoginDTO;
import org.com.entity.User;
import org.com.exception.BaseException;
import org.com.mapper.UserMapper;
import org.com.properties.WechatProperties;
import org.com.service.UserService;
import org.com.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WechatProperties wechatProperties;

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String openId = getOpenid(userLoginDTO.getCode());

        if (openId == null){
            throw new BaseException("找不到 openId");
        }

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getOpenId, openId);

        User user = this.getOne(wrapper);
        if (user == null){
            user = User.builder()
                    .openId(openId)
                    .build();
            this.save(user);
        }

        return user;
    }

    private String getOpenid(String code){
        Map<String, String> map = new HashMap<>();
        map.put("appid", wechatProperties.getAppid());
        map.put("secret", wechatProperties.getAppSecret());
        map.put("js_code",code);
        map.put("grant_type", "authorization_code");

        String json = HttpClientUtil.doGet(WX_LOGIN, map);
        JSONObject parse = JSON.parseObject(json);

        return parse.getString("openid");
    }
}
