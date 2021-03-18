package com.prashant.project;

import com.prashant.project.domain.TrainingClass;
import com.prashant.project.domain.TrainingClassSchedule;
import com.prashant.project.domain.User;
import com.prashant.project.enums.ClassEnum;
import com.prashant.project.requests.BookClassRequest;
import com.prashant.project.requests.CancelClassRequest;
import com.prashant.project.responses.BookClassResponse;
import com.prashant.project.responses.CancelClassResponse;
import com.prashant.project.services.impl.DefaultBookingService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

/**
 * created by prashant246 on 2021-03-18
 */

public class Main {
    public static void main(String[] args) {
        /**
         * This will prepare data with three users and three classes
         */
        prepareData();

        DefaultBookingService bookingService = new DefaultBookingService();

        // Three booking request for class id 3
        BookClassRequest request1 = BookClassRequest.builder().userName("a").classToBook(3).build();
        BookClassRequest request2 = BookClassRequest.builder().userName("b").classToBook(3).build();
        BookClassRequest request3 = BookClassRequest.builder().userName("c").classToBook(3).build();

        // This will successfully book the class
        BookClassResponse response1 = bookingService.bookClass(request1);
        System.out.println(response1);

        // This will successfully book the class
        BookClassResponse response2 = bookingService.bookClass(request2);
        System.out.println(response2);

        // This will fail to book class since slots are full and add user to waiting list
        BookClassResponse response3 = bookingService.bookClass(request3);
        System.out.println(response3);

        CancelClassRequest request4 = CancelClassRequest.builder().classToCancel(3).userName("a").build();

        // This will cancel the booking of user a and add user c to the confirmed list
        CancelClassResponse response5 = bookingService.cancelBooking(request4);
        System.out.println(response5);

        // This will fail to book since slots are book
        BookClassResponse response6 = bookingService.bookClass(request3);
        System.out.println(response6);

    }

    static void prepareData() {
        //create user
        User user1 = User.builder().name("A").userName("a").build();
        User user2 = User.builder().name("B").userName("b").build();
        User user3 = User.builder().name("C").userName("c").build();

        Data.addUser(user1);
        Data.addUser(user2);
        Data.addUser(user3);

        LocalTime TwoPM = LocalTime.of(14, 00);
        LocalTime FourPM = LocalTime.of(16, 00);
        LocalTime SixPM = LocalTime.of(18, 00);

        LocalTime OneThirtyPM = LocalTime.of(13, 30);
        LocalTime ThreeThirtyPM = LocalTime.of(15, 30);
        LocalTime FiveThirtyPM = LocalTime.of(17, 30);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // create classes
        TrainingClass tc1 = TrainingClass.builder()
                .className(ClassEnum.YOGA)
                .classStartTime(TwoPM)
                .cancelCutoffTime(OneThirtyPM)
                .id(1L)
                .capacity(2)
                .build();
        TrainingClass tc2 = TrainingClass.builder()
                .className(ClassEnum.GYM)
                .classStartTime(FourPM)
                .cancelCutoffTime(ThreeThirtyPM)
                .id(2L)
                .capacity(2)
                .build();
        TrainingClass tc3 = TrainingClass.builder()
                .className(ClassEnum.DANCE)
                .classStartTime(SixPM)
                .cancelCutoffTime(FiveThirtyPM)
                .id(3L)
                .capacity(2)
                .build();

        Data.addTrainingClass(tc1);
        Data.addTrainingClass(tc2);
        Data.addTrainingClass(tc3);
        // create class for today date

        LocalDate todayDate = LocalDate.now();

        TrainingClassSchedule tcs1 = TrainingClassSchedule.builder()
                .confirmedUser(new ArrayList<>())
                .id(1l)
                .slotsBooked(0)
                .trainingClass(tc1)
                .trainingDate(todayDate)
                .waitingUser(new LinkedList<>())
                .build();
        TrainingClassSchedule tcs2 = TrainingClassSchedule.builder()
                .confirmedUser(new ArrayList<>())
                .id(2l)
                .slotsBooked(0)
                .trainingClass(tc2)
                .trainingDate(todayDate)
                .waitingUser(new LinkedList<>())
                .build();
        TrainingClassSchedule tcs3 = TrainingClassSchedule.builder()
                .confirmedUser(new ArrayList<>())
                .id(3l)
                .slotsBooked(0)
                .trainingClass(tc3)
                .trainingDate(todayDate)
                .waitingUser(new LinkedList<>())
                .build();

        Data.addTrainingSchedule(tcs1);
        Data.addTrainingSchedule(tcs2);
        Data.addTrainingSchedule(tcs3);
    }
}
