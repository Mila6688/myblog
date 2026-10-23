package com.myblog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

@TableName("message")   // 绑定数据库里面的 message 表
public class Message {
    @TableId(type = IdType.AUTO)  // 标记主键，MySQL自增
    private Long id;   // 已经把 long → Long 包装类型

    private String content;
    private LocalDateTime createdAt;

    public Message(){
    }

    public Message(Long id, String content, LocalDateTime createdAt){
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
}
