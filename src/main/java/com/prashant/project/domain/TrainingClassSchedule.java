package com.prashant.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 * created by prashant246 on 2021-03-18
 */

/**
 * This will store the instance of all classes date wise. For each date we can create training classes
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingClassSchedule {

    Long id;

    // training class to be conducted
    TrainingClass trainingClass;

    // number of slots booked for class
    int slotsBooked;

    // date for class
    LocalDate trainingDate;

    // list of users who are confirmed for class
    List<User> confirmedUser;

    // list of users in waiting list
    Queue<User> waitingUser;
}
