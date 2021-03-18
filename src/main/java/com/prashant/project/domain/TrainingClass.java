package com.prashant.project.domain;

import com.prashant.project.enums.ClassEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * created by prashant246 on 2021-03-18
 */

/**
 * This will consist of the repetative classes at specific time.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingClass {
    Long id;
    ClassEnum className;

    //capacity of the class
    int capacity;

    // start time of the class
    LocalTime classStartTime;

    // cancel cutoff for the class
    LocalTime cancelCutoffTime;

}
