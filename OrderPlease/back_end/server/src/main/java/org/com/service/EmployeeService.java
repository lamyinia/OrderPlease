package org.com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.com.dto.EmployeeLoginDTO;
import org.com.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService extends IService<Employee> {
    Employee login(EmployeeLoginDTO employeeLoginDTO);
}
