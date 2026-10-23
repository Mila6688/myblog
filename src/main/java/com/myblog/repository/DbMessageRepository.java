package com.myblog.repository;

import com.myblog.mapper.MessageMapper;
import com.myblog.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DbMessageRepository implements MessageRepository {

    private final MessageMapper messageMapper;

    public DbMessageRepository(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public Message save(Message message) {
        messageMapper.insert(message);
// 主键由 MySQL 生成并回填到 message.id
        return message;
    }

    @Override
    public List<Message> findAll() {
        return messageMapper.selectList(null);
// null = 无过滤条件,查全部
    }

    @Override
    public Optional<Message> findById(Long id) {
        return Optional.ofNullable(messageMapper.selectById(id));
    }
}

