package org.com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.com.annotation.AutoFillSetter;
import org.com.dto.EmployeeDTO;
import org.com.dto.EmployeeLoginDTO;
import org.com.dto.EmployeePageQueryDTO;
import org.com.entity.Employee;
import org.com.enumeration.OperationType;
import org.com.result.PageResult;
import org.com.result.Result;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService extends IService<Employee> {
    void addEmployee(EmployeeDTO employeeDTO);
    void addEmployee(Employee employee);
    Employee login(EmployeeLoginDTO employeeLoginDTO);
    Result<PageResult> selectPage(EmployeePageQueryDTO employeePageQueryDTO);
}
