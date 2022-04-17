package com.daelim.Limbook.web.repository;


import com.daelim.Limbook.web.controller.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User save(User user){

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user");

        Map<String, Object> params = new HashMap<>();

        params.put("user_id", user.getId());
        params.put("user_pw", user.getPw());
        params.put("user_name", user.getName());
        params.put("user_phone", user.getPhone());
        params.put("user_department", user.getDepartment());

        jdbcInsert.execute(params);

        return user;
    }
}
