package com.daelim.Limbook.web.service.users;

import com.daelim.Limbook.web.controller.dto.UserDTO.UserLoginDTO;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.repository.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    //회원가입
    public User signUp(User user){
        return userRepository.save(user);
    }

    //로그인
    public User login(UserLoginDTO userLoginDTO){
        return userRepository.findbyId(userLoginDTO.getUser_id())
                .filter(user -> user.getPw().equals(userLoginDTO.getUser_pw()))
                .orElse(null);
    }

}
