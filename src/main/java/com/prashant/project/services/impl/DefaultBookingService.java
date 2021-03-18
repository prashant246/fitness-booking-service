package com.prashant.project.services.impl;

import com.prashant.project.Data;
import com.prashant.project.domain.TrainingClassSchedule;
import com.prashant.project.domain.User;
import com.prashant.project.requests.BookClassRequest;
import com.prashant.project.requests.CancelClassRequest;
import com.prashant.project.responses.BookClassResponse;
import com.prashant.project.responses.CancelClassResponse;

/**
 * created by prashant246 on 2021-03-18
 */

public class DefaultBookingService {

    public DefaultBookingService(){

    }

    public BookClassResponse bookClass(BookClassRequest request) {
        User user = Data.getUserByUserName(request.getUserName());
        TrainingClassSchedule classToBook = Data.getTrainingClassById(request.getClassToBook());

        BookClassResponse response = BookClassResponse.builder()
                .userName(request.getUserName())
                .trainingClass(classToBook).build();

        if (Data.isClassAvailable(classToBook)) {
            Data.addUserToClassAndUpdateSlot(user, classToBook);
            response.setBookingConfirmed(true);
        } else {
            Data.addUserToClassWaitingList(user, classToBook);
            response.setBookingConfirmed(false);
            response.setReason("Class slots full");
        }


        return response;
    }

    public CancelClassResponse cancelBooking(CancelClassRequest request) {
        User user = Data.getUserByUserName(request.getUserName());
        TrainingClassSchedule classToCancel = Data.getTrainingClassById(request.getClassToCancel());
        CancelClassResponse response = CancelClassResponse.builder()
                .userName(request.getUserName())
                .trainingClass(classToCancel).build();


        if (Data.isClassCancellationAllowed(classToCancel)) {
            Data.removeUserFromClassAndUpdateSlot(user, classToCancel);
            response.setCancelConfirmed(true);
            Data.addUserToClassAndUpdateSlot(classToCancel.getWaitingUser().remove(), classToCancel);
        } else {
            response.setCancelConfirmed(false);
            response.setReason("CutOff time breached");
        }
        return response;
    }
}
