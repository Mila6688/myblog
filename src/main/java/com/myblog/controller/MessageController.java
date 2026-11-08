package com.myblog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myblog.dto.PageResult;
import com.myblog.model.Message;
import com.myblog.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * 留言前台(只做接待:收参数 → 喊 Service → 返回结果)
 * 一行 SQL 都不出现 —— Week05 立下的规矩,Week07 继续遵守。
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 创建留言
     * POST /messages   body: {"content": "..."}
     * 返回 201 + 带 id 和 createdAt 的完整对象
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Message create(@RequestBody Message message) {
        return messageService.create(message);
    }

    /**
     * 搜索 + 分页
     * GET /messages                       → 默认第 1 窗、每窗 5 条
     * GET /messages?keyword=hello         → 含 hello 的第 1 窗
     * GET /messages?keyword=hello&page=2&size=2 → 过滤 + 翻页
     *
     * 返回统一瘦身载荷:{ items, total, page, size }
     * 查不到返回 items:[] 的"诚实空",不报错。
     */
    @GetMapping
    public PageResult<Message> list(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") long page,
            @RequestParam(defaultValue = "5") long size) {
        Page<Message> result = messageService.search(keyword, page, size);
        return PageResult.of(result);
    }

    /**
     * 查询单条
     * GET /messages/1   → 200 + 对象
     * GET /messages/999 → 404 "message not found"(不撒谎)
     */
    @GetMapping("/{id}")
    public Message getOne(@PathVariable Long id) {
        return messageService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "message not found"));
    }
}
