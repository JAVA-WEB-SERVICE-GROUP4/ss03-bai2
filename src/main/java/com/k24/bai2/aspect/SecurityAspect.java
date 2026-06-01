package com.k24.bai2.aspect..aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    private String currentUserRole = "VIP"; // Giả lập user chỉ là VIP

    @Before("execution(* com.example.aopdemo.service.ProductService.add*(..))")
    public void verifyUser(JoinPoint joinPoint) {
        if (!"ADMIN".equals(currentUserRole)) {
            // Ném ngoại lệ để chặn ngay lập tức
            throw new RuntimeException("!! TRUY CẬP BỊ TỪ CHỐI !! User không có quyền.");
        }
    }
}
