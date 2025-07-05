package org.com;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.com.entity.Category;
import org.com.entity.Employee;
import org.com.entity.User;
import org.com.mapper.EmployeeMapper;
import org.com.service.CategoryService;
import org.com.service.EmployeeService;
import org.com.utils.AliYunOssUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@SpringBootTest
class ServerApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    AliYunOssUtil aliYunOssUtil;
    @Autowired
    RedisTemplate redisTemplate;

    @Data
    @AllArgsConstructor
    class One implements Serializable {
        Integer number;
        String str;
    }

    @Test
    void testRedis(){
        redisTemplate.opsForValue().set("key_one", new One(99, "one"));
    }

    @Test
    void TestRemove() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<Category>()
                .eq(Category::getName, "鼠标");
    }

    @Test
    void pageTest(){
        Page<Employee> page = employeeService.page(new Page<>(2, 2));

        List<Employee> records = page.getRecords();
        records.forEach(System.out::println);
    }

    @Test
    void testParameter(String y, Integer... types){
        System.out.println(types.getClass());
    }

    @Test
    void test(){
        System.out.println(System.currentTimeMillis());
    }
}
