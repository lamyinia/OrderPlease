package org.com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.com.entity.Employee;
import org.com.mapper.EmployeeMapper;
import org.com.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
