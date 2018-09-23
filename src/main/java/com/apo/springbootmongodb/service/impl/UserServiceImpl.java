package com.apo.springbootmongodb.service.impl;

import com.apo.springbootmongodb.service.UserService;
import com.apo.springbootmongodb.model.User;
import com.apo.springbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = userRepository.findAll();
        return listUsers;
    }

    @Override
    public List<String> getUsersUpcomingEvent() {
        List<User> listUpcomingBirthdays = getUsersEvents(true);
        List<User> listUpcomingNamedays = getUsersEvents(false);

        if(listUpcomingNamedays.isEmpty() && listUpcomingBirthdays.isEmpty())
            return null;
        else if((listUpcomingNamedays.isEmpty() && !listUpcomingBirthdays.isEmpty()) ||
                 listUpcomingBirthdays.get(0).getDate().getBirthMonth() < listUpcomingNamedays.get(0).getDate().getNameMonth()){
            return createStringList(listUpcomingBirthdays, true);
        }
        else if((!listUpcomingNamedays.isEmpty() && listUpcomingBirthdays.isEmpty()) ||
                 listUpcomingBirthdays.get(0).getDate().getBirthMonth() > listUpcomingNamedays.get(0).getDate().getNameMonth()){
            return createStringList(listUpcomingNamedays, false);
        }

        // if we reached here, both months are equal
        if(listUpcomingBirthdays.get(0).getDate().getBirthDay() > listUpcomingNamedays.get(0).getDate().getNameDay())
            return createStringList(listUpcomingNamedays, false);

        // by default if birthday and nameday is the same day, we return birthday
        return createStringList(listUpcomingBirthdays, true);
    }

    private List<User> getUsersEvents(boolean flag) {
        /*
            flag = false: get users namedays
            flag = true: get users birthdays
        */
        LocalDate localDate = LocalDate.now();
        int currentDay = localDate.getDayOfMonth();
        int currentMonth = localDate.getMonthValue();

        List<User> listAllUsersEvents;
        List<User> listUpcomingEvents = new ArrayList<User>();

        if(flag)
            listAllUsersEvents = userRepository.findByBirthdayMonthGreaterThan(currentMonth-1);
        else
            listAllUsersEvents = userRepository.findByNamedayMonthGreaterThan(currentMonth-1);

        if(!listAllUsersEvents.isEmpty()){
            int month = 13, day = 32, userMonth, userDay;

            for(User user: listAllUsersEvents){
                if(flag)
                    userMonth = user.getDate().getBirthMonth();
                else
                    userMonth = user.getDate().getNameMonth();

                if(userMonth > month){
                    // user's event is longer
                    continue;
                }

                if(flag)
                    userDay = user.getDate().getBirthDay();
                else
                    userDay = user.getDate().getNameDay();

                if(userMonth == currentMonth && userDay < currentDay){
                    // prevent adding event from this month which already passed
                    continue;
                }
                if(userMonth < month || userDay < day){
                    // current event in listUpcomingEvents is no longer the nearest event
                    listUpcomingEvents.clear();
                }
                month = userMonth;
                day = userDay;
                listUpcomingEvents.add(user);
            }
        }
        return listUpcomingEvents;
    }

    private List<String> createStringList(List<User> listUsers, boolean flag){
        /*
            flag = false: create array from namedays
            flag = true: create array from birthdays
        */
        List<String> list = new ArrayList<String>();
        if(flag){
            for(User user: listUsers){
                list.add("U. " + user.getDate().getBirthDay() + "." + user.getDate().getBirthMonth() +
                         " " + user.getFirstName() + " " + user.getLastName());
            }
        }
        else{
            for(User user: listUsers){
                list.add("I. " + user.getDate().getNameDay() + "." + user.getDate().getNameMonth() +
                        " " + user.getFirstName() + " " + user.getLastName());
            }
        }

        return list;
    }
}
