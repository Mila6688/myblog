package com.myblog.controller;

import com.myblog.dto.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * 门口的统一道歉话术:任何 Service 抛出的"找不到",
 * 都在这里被翻译成 404 + Result 骨架的 JSON —— 绝不吐 HTML 错误页。
 * 成功和失败共用同一个 {code, message, data} 骨架,
 * 这正是 §4.3 "Same skeleton? Yes" 的实现担当。
 */
@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public Result<Void> notFound(NoSuchElementException ex) {
        return Result.fail(404, ex.getMessage() == null ? "not found" : ex.getMessage());
    }
}
