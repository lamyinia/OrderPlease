package org.com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.com.annotation.AutoFillSetter;
import org.com.constant.MessageConstant;
import org.com.constant.PasswordConstant;
import org.com.constant.StatusConstant;
import org.com.dto.EmployeeDTO;
import org.com.dto.EmployeeLoginDTO;
import org.com.dto.EmployeePageQueryDTO;
import org.com.entity.Employee;
import org.com.enumeration.OperationType;
import org.com.exception.AccountLockedException;
import org.com.exception.AccountNotFoundException;
import org.com.exception.PasswordErrorException;
import org.com.mapper.EmployeeMapper;
import org.com.result.PageResult;
import org.com.service.EmployeeService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        Employee employee = this.getOne(new LambdaQueryWrapper<Employee>()
                .eq(Employee::getUsername, username));  // select by username

        if (employee == null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        if (employee.getStatus() == StatusConstant.DISABLE){
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        return employee;
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setStatus(StatusConstant.ENABLE);
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        EmployeeService proxy = (EmployeeService) AopContext.currentProxy();
        proxy.addEmployee(employee); // 通过代理调用，触发切面
    }

    @Override
    @AutoFillSetter(value = OperationType.INSERT)
    public void addEmployee(Employee employee) {
        this.save(employee);
    }

    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
