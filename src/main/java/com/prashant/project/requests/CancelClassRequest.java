package com.prashant.project.requests;

import com.prashant.project.domain.TrainingClassSchedule;
import lombok.Builder;
import lombok.Data;

/**
 * created by prashant246 on 2021-03-18
 */

@Data
@Builder
public class CancelClassRequest {

    String userName;
    long classToCancel;
    long cancelInitiateTime;
}
