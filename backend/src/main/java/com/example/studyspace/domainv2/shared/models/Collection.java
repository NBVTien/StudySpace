package com.example.studyspace.domainv2.shared.models;

import lombok.Value;

import java.util.List;

@Value
public class Collection<T> {
    List<T> items;
    int totalCount;
    int pageSize;
    int pageNumber;
    int totalPages;

    public Collection(List<T> items, int totalCount, int pageSize, int pageNumber) {
        this.items = items;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
    }
}
