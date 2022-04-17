package com.daelim.Limbook.web.service;

import com.daelim.Limbook.web.controller.domain.User;
import com.daelim.Limbook.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Repository
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    //회원가입
    public User signUp(User user){
        return userRepository.save(user);
    }

}
