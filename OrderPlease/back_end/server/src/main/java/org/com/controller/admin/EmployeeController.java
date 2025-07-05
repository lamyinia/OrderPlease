package org.com.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.com.constant.JwtClaimsConstant;
import org.com.dto.EmployeeDTO;
import org.com.dto.EmployeeLoginDTO;
import org.com.dto.EmployeePageQueryDTO;
import org.com.entity.Employee;
import org.com.properties.JwtProperties;
import org.com.result.PageResult;
import org.com.result.Result;
import org.com.service.EmployeeService;
import org.com.utils.JwtUtil;
import org.com.vo.EmployeeLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api("员工信息")
@Slf4j
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * @param employeeLoginDTO
     * @return {@link Result }<{@link EmployeeLoginVO }> 返回 id、userName、name、token
     * 如果验证正确账号和密码，返回 token
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        Employee employee = employeeService.login(employeeLoginDTO);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());

        String token = JwtUtil
                .createJwt(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO
                .builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    @ApiOperation("测试")
    @GetMapping("/test")
    public String getTest(){
        return DigestUtils.md5DigestAsHex("a123456".getBytes());
    }

    @ApiOperation("新增员工")
    @PostMapping("/add")
    public Result addEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.addEmployee(employeeDTO);
        return Result.success();
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Result<PageResult> selectPage(EmployeePageQueryDTO employeePageQueryDTO){
        PageResult result = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(result);
    }

    // TODO 启用禁用
}
