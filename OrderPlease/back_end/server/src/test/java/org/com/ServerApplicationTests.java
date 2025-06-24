package org.com;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.com.entity.Employee;
import org.com.mapper.EmployeeMapper;
import org.com.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.util.List;

@SpringBootTest
class ServerApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    EmployeeService employeeService;

    @Test
    void insertTest() {
        for (int i = 6; i <= 6; ++ i){
            employeeService.save(new Employee().builder()
                    .username("admin_by_" + String.valueOf(i))
                    .password("123456")
                    .name("小王")
                    .phone("13202010201")
                    .sex("0")
                    .identityCard("440101199001010047")
                    .build());
        }
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
        Class<EmployeeService> aClass = EmployeeService.class;
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.printf("%s %s\n", declaredMethod.getName(), declaredMethod.getAnnotations());
        }
    }
}
