package org.com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.com.dto.EmployeePageQueryDTO;
import org.com.entity.Employee;

public interface EmployeeMapper extends BaseMapper<Employee> {
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}
