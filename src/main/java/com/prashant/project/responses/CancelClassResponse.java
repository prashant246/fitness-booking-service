package com.prashant.project.responses;

import com.prashant.project.domain.TrainingClassSchedule;
import lombok.Builder;
import lombok.Data;

/**
 * created by prashant246 on 2021-03-18
 */

@Builder
@Data
public class CancelClassResponse {

    String userName;
    boolean cancelConfirmed;
    TrainingClassSchedule trainingClass;
    String reason;

    @Override
    public String toString() {
        return "CancelClassResponse{" +
                "userName='" + userName + '\'' +
                ", cancelConfirmed=" + cancelConfirmed +
                ", reason='" + reason + '\'' +
                '}';
    }
}
