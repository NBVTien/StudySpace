package com.example.studyspace.application.common.models;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * A generic class representing a paginated list response.
 *
 * @param <T> the type of the items in the list
 *
 * @version 1.0
 */
@Value
@Builder
public class PaginatedResult<T> {
    /**
     * The list of items.
     */
    List<T> data;
    /**
     * The total number of items.
     */
    int total;
    /**
     * The total number of pages.
     */
    int totalPages;
    /**
     * The current page number.
     */
    int page;
    /**
     * The size of each page.
     */
    int pageSize;
}
