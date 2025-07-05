package org.com;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.com.entity.Category;
import org.com.entity.Employee;
import org.com.mapper.EmployeeMapper;
import org.com.service.CategoryService;
import org.com.service.EmployeeService;
import org.com.utils.AliYunOssUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    @Test
    void testUpload(){
        for (int i = 2; i <= 27; ++ i){
            String filePath = "D:\\git-clone\\黑马Java项目-苍穹外卖\\资料\\资料\\day03\\图片资源\\" + String.valueOf(i) + ".png";
            Path path = Path.of(filePath);
            if(!Files.exists(path)){
                System.err.println("文件不存在: " + path.toAbsolutePath());
                // 或者抛出断言错误，让测试失败
                Assertions.fail("文件不存在: " + filePath);
                return;
            }

            try {
                byte[] bytes = Files.readAllBytes(Path.of(filePath));
                aliYunOssUtil.upload(bytes, "order-please/imgs/"  + String.valueOf(i) + ".png");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
