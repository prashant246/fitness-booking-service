package com.prashant.project.domain;

import com.prashant.project.enums.ClassStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by prashant246 on 2021-03-18
 */

/**
 * To store the status of the class for users
 */

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserClassStatus {

    String userName;
    long classId;
    ClassStatus status;
}
