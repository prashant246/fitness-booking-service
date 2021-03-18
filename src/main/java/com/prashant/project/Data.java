package com.prashant.project;

import com.prashant.project.domain.TrainingClass;
import com.prashant.project.domain.TrainingClassSchedule;
import com.prashant.project.domain.User;
import com.prashant.project.domain.UserClassStatus;
import com.prashant.project.enums.ClassStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * created by prashant246 on 2021-03-18
 */

public class Data {

    static List<User> users = new ArrayList<>();
    static List<TrainingClass> trainingClasses = new ArrayList<>();
    static HashMap<Long, TrainingClassSchedule> trainingClassSchedulesMap = new HashMap<>();
    static List<UserClassStatus> userClassStatusList = new ArrayList<>();

    public static void addUser(User user) {
        users.add(user);
    }

    public static void addTrainingClass(TrainingClass trainingClass) {
        trainingClasses.add(trainingClass);
    }

    public static void addTrainingSchedule(TrainingClassSchedule trainingClassSchedule) {
        trainingClassSchedulesMap.put(trainingClassSchedule.getId(), trainingClassSchedule);
    }

    public static User getUserByUserName(String userName) {
        return users.stream().filter(user -> user.getUserName().equalsIgnoreCase(userName)).findFirst().get();
    }

    public static TrainingClassSchedule getTrainingClassById(long id) {
        return trainingClassSchedulesMap.get(id);
    }

    public static void addUserToClassAndUpdateSlot(User user, TrainingClassSchedule trainingClassSchedule) {
        TrainingClassSchedule trainingClassSchedule1 = trainingClassSchedulesMap.get(trainingClassSchedule.getId());
        trainingClassSchedule1.getConfirmedUser().add(user);
        Optional<UserClassStatus> userClassStatus = getUserClassStatus(user.getUserName(), trainingClassSchedule.getId());
        if (userClassStatus.isPresent()) {
            userClassStatus.get().setStatus(ClassStatus.CONFIRMED);
        } else {
            userClassStatusList.add(UserClassStatus.builder().userName(user.getUserName()).classId(trainingClassSchedule.getId()).status(ClassStatus.CONFIRMED).build());
        }
        trainingClassSchedule.setSlotsBooked(trainingClassSchedule.getSlotsBooked() + 1);
    }

    private static Optional<UserClassStatus> getUserClassStatus(String userName, long classId) {
        return userClassStatusList.stream().filter(
                userClassStatus -> userClassStatus.getClassId() == classId && userClassStatus.getUserName().equals(userName))
                .findFirst();
    }

    public static void addUserToClassWaitingList(User user, TrainingClassSchedule trainingClassSchedule) {
        TrainingClassSchedule trainingClassSchedule1 = trainingClassSchedulesMap.get(trainingClassSchedule.getId());
        trainingClassSchedule1.getWaitingUser().add(user);
        Optional<UserClassStatus> userClassStatus = getUserClassStatus(user.getUserName(), trainingClassSchedule.getId());
        if (userClassStatus.isPresent()) {
            userClassStatus.get().setStatus(ClassStatus.WAITING);
        } else {
            userClassStatusList.add(UserClassStatus.builder().userName(user.getUserName()).classId(trainingClassSchedule.getId()).status(ClassStatus.WAITING).build());
        }
    }

    public static void removeUserFromClassAndUpdateSlot(User user, TrainingClassSchedule trainingClassSchedule) {
        TrainingClassSchedule trainingClassSchedule1 = trainingClassSchedulesMap.get(trainingClassSchedule.getId());
        trainingClassSchedule1.getConfirmedUser().remove(user);
        Optional<UserClassStatus> userClassStatus = getUserClassStatus(user.getUserName(), trainingClassSchedule.getId());
        if (userClassStatus.isPresent()) {
            userClassStatus.get().setStatus(ClassStatus.CANCELLED);
        } else {
            userClassStatusList.add(UserClassStatus.builder().userName(user.getUserName()).classId(trainingClassSchedule.getId()).status(ClassStatus.CANCELLED).build());
        }
        trainingClassSchedule.setSlotsBooked(trainingClassSchedule.getSlotsBooked() - 1);
    }

    public static boolean isClassAvailable(TrainingClassSchedule classToBook) {
        if (classToBook.getSlotsBooked() < classToBook.getTrainingClass().getCapacity()) {
            return true;
        }
        return false;
    }

    public static boolean isClassCancellationAllowed(TrainingClassSchedule classToCancel) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime cancelCutoffTime = LocalDateTime.of(classToCancel.getTrainingDate(), classToCancel.getTrainingClass().getCancelCutoffTime());

        if (cancelCutoffTime.isAfter(currentTime)) {
            return true;
        }
        return false;
    }

}
