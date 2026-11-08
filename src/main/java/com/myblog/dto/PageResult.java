package com.myblog.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

public record PageResult<T>(List<T> items, long total, long page, long size) {

    public static <T> PageResult<T> of(Page<T> p) {
        return new PageResult<>(p.getRecords(), p.getTotal(),
                p.getCurrent(), p.getSize());
    }
}
