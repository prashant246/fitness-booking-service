package com.prashant.project.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by prashant246 on 2021-03-18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookClassRequest {

    String userName;
    long classToBook;
}
