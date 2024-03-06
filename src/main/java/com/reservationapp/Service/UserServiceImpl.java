package com.reservationapp.Service;

import com.reservationapp.Entity.UserRegistration;
import com.reservationapp.Repository.UserRegistrationRepository;
import com.reservationapp.payload.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    public UserRegistrationDto createUser(UserRegistration userRegistration){
        userRegistrationRepository.save(userRegistration);
        return null;
    }

    public UserRegistration getUseById(long id) {
      return  userRegistrationRepository.findById(id).get();
    }
}
