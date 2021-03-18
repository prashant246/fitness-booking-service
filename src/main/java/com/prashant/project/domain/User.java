package com.prashant.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * created by prashant246 on 2021-03-18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    String name;
    String userName;

}
