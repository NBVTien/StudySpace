package com.example.studyspace.application.common.models;

import lombok.Builder;
import lombok.Value;

/**
 * A DTO class representing a request for a paginated list.
 * It contains the page number and the size of each page.
 *
 * @version 1.0
 */
@Value
@Builder
public class ListQuery {
    /**
     * The page number to retrieve.
     */
    int page;
    /**
     * The size of each page.
     */
    int pageSize;
}
