package org.com.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.com.annotation.AutoFillSetter;
import org.com.constant.AutoFillConstant;
import org.com.context.BaseContext;
import org.com.enumeration.OperationType;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class AutoFillSetterAspect {

    @Pointcut("execution(* org.com.service.impl..*(..)) && @annotation(org.com.annotation.AutoFillSetter)")
    public void fillSetter() {}

    @Before("fillSetter()")
    public void autoFillSetter(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFillSetter autoFill = signature.getMethod().getAnnotation(AutoFillSetter.class);

        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0){
            return;
        }

        Object entity = args[0];
        Long currentId = BaseContext.getCurrentId();
        log.info("切面: 自动填充 createUserId");
        if (autoFill.value() == OperationType.INSERT){
            try {
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setCreateUser.invoke(entity, currentId);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
