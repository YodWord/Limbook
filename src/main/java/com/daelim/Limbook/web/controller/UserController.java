package com.daelim.Limbook.web.controller;

import com.daelim.Limbook.web.SessionConst;
import com.daelim.Limbook.web.controller.dto.UserDTO.UserLoginDTO;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.UserDTO.UserSignUpDTO;
import com.daelim.Limbook.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    //회원가입
    //TODO: bindingResult로 오류 컨트롤
    @PostMapping
    public HashMap<String, Object> signUp(@RequestBody @Validated UserSignUpDTO userSignUpDTO, BindingResult bindingResult)throws Exception{

        HashMap<String, Object> response = new HashMap<>();


        if(bindingResult.hasErrors()){
            response.put("result","오류");
            return response;
        }


        User user = new User(userSignUpDTO);

        userService.signUp(user);

        HashMap<String, Object> userResponse = new HashMap<>();
        userResponse.put("user_id", user.getId());
        userResponse.put("user_pw", user.getPw());
        userResponse.put("user_name", user.getName());
        userResponse.put("user_phone", user.getPhone());
        userResponse.put("user_department", user.getDepartment());

        response.put("result","성공");
        response.put("user", userResponse);

        return response;
    }

    //TODO: 아이디 중복체크 (필요할시)



    //로그인
    @PostMapping("/login")
    public HashMap<String,Object> login(@RequestBody @Validated UserLoginDTO userLoginDTO, BindingResult bindingResult, HttpServletRequest request) throws Exception{

        HashMap<String,Object> response = new HashMap<>();

        if(bindingResult.hasErrors()){
            response.put("result","오류");
            return response;
        }

        User user = userService.login(userLoginDTO);

        if(user == null || !user.getId().equals(userLoginDTO.getUser_id())
                && user.getPw().equals(userLoginDTO.getUser_pw())){
            response.put("result","입력값을 확인하세요");
            return response;
        }

        response.put("user_id", user.getId());
        response.put("user_name", user.getName());
        response.put("user_phone", user.getPhone());
        response.put("user_department", user.getDepartment());

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER,user);

        return response;
    }

    //로그아웃
    @PostMapping("/logout")
    public HashMap<String, String> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");

        return resultMap;

    }



}
