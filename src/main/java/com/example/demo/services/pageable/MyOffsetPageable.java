package com.example.demo.services.pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class MyOffsetPageable extends PageRequest {
    private int offset;

    public MyOffsetPageable(int page, int size, Sort sort, int offset) {
        super(page, size, sort);
        this.offset = offset;
    }

    @Override
    public long getOffset() {
        return offset;
    }
}
