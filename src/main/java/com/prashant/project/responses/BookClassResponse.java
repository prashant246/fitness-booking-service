package com.prashant.project.responses;

import com.prashant.project.domain.TrainingClassSchedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by prashant246 on 2021-03-18
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookClassResponse {

    String userName;
    TrainingClassSchedule trainingClass;
    boolean bookingConfirmed;
    String reason;

    @Override
    public String toString() {
        return "BookClassResponse{" +
                "userName='" + userName + '\'' +
                ", bookingConfirmed=" + bookingConfirmed +
                ", reason='" + reason + '\'' +
                '}';
    }
}
