package com.myblog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myblog.mapper.MessageMapper;
import com.myblog.model.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 留言业务员(方案 A:直接依赖 MessageMapper,不再经过 Repository 层)
 */
@Service
public class MessageService {

    private final MessageMapper messageMapper;   // 唯一的依赖

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    /**
     * 创建留言(POST /messages)
     * 主键 id 由 MySQL 自增生成并回填到 message.id
     */
    public Message create(Message message) {
        if (message.getContent() == null || message.getContent().isBlank()) {
            throw new IllegalArgumentException("content 不能为空");
        }
        if (message.getCreatedAt() == null) {
            message.setCreatedAt(LocalDateTime.now());
        }
        messageMapper.insert(message);
        return message;
    }

    /**
     * 查询单条(GET /messages/{id})
     * 查不到返回 Optional.empty(),由 Controller 决定如何响应
     */
    public Optional<Message> getById(Long id) {
        return Optional.ofNullable(messageMapper.selectById(id));
    }

    /**
     * 搜索 + 分页(GET /messages?keyword=&page=&size=)
     * 一条路径,条件按需累积:
     *   - keyword 有值 → 追加 LIKE 条件
     *   - 始终按创建时间倒序(新的在前)
     * 过滤、切片、计数全部发生在 MySQL 里
     */
    public Page<Message> search(String keyword, long page, long size) {
        LambdaQueryWrapper<Message> qw = new LambdaQueryWrapper<>();
        if (keyword != null && !keyword.isBlank()) {
            qw.like(Message::getContent, keyword);
        }
        qw.orderByDesc(Message::getCreatedAt);
        return messageMapper.selectPage(new Page<>(page, size), qw);
    }
}
