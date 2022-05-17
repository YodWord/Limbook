package com.daelim.Limbook.web.controller;

import com.daelim.Limbook.web.SessionConst;
import com.daelim.Limbook.web.argumentResolver.Login;
import com.daelim.Limbook.web.controller.dto.UserDTO.UserLoginDTO;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.controller.dto.UserDTO.UserSignUpDTO;
import com.daelim.Limbook.web.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    /**
     *   회원가입
     *
     * */
    @PostMapping
    public ResponseEntity<Object> signUp(@RequestBody @Validated UserSignUpDTO userSignUpDTO, BindingResult bindingResult)throws Exception{

        HashMap<String, Object> response = new HashMap<>();


        if(bindingResult.hasErrors()){
            response.put("result","입력값 확인 필요.");

            return ResponseEntity.badRequest().body(response);
        }


        User user = User.createUserByDTO(userSignUpDTO);

        userService.signUp(user);

        HashMap<String, Object> userResponse = new HashMap<>();
        userResponse.put("user_id", user.getId());
        userResponse.put("user_pw", user.getPw());
        userResponse.put("user_name", user.getName());
        userResponse.put("user_phone", user.getPhone());
        userResponse.put("user_department", user.getDepartment());

        response.put("result","성공");
        response.put("user", userResponse);

        return ResponseEntity.ok().body(response);
    }

    //TODO: 아이디 중복체크 (필요할시)

    /**
     *   로그인
     *
     * */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Validated UserLoginDTO userLoginDTO, BindingResult bindingResult,
                                        HttpServletRequest request) throws Exception{

        HashMap<String,Object> response = new HashMap<>();

        if(bindingResult.hasErrors()){
            response.put("result","입력값 확인 필요");
            return ResponseEntity.badRequest().body(response);
        }

        User user = userService.login(userLoginDTO);

        if(user == null || !(user.getId().equals(userLoginDTO.getUser_id()) && user.getPw().equals(userLoginDTO.getUser_pw()))){
            response.put("result","아이디와 비밀번호를 확인해 주세요.");
            return ResponseEntity.badRequest().body(response);
        }

        HashMap<String, String> userResponse = new HashMap<>();

        userResponse.put("user_id", user.getId());
        userResponse.put("user_name", user.getName());
        userResponse.put("user_phone", user.getPhone());
        userResponse.put("user_department", user.getDepartment());

        response.put("result","성공");
        response.put("user",userResponse);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, user);

        return ResponseEntity.ok().body(response);
    }

    /**
     *   로그아웃
     *
     * */
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "성공");

        return ResponseEntity.ok().body(result);

    }
    //TODO: 회원 수정? 삭제? 조회?

    @GetMapping
    public ResponseEntity<Object> findByUserId(@Login User user) throws Exception{
        HashMap<String, Object> response = new HashMap<>();

        if(user == null){
            response.put("result", "로그인이 필요합니다.");
            return ResponseEntity.badRequest().body(response);
            //throw new Exception("로그인이 필요합니다.");
        }

        return null;
    }


}
