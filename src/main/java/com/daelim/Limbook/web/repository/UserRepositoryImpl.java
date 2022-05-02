package com.daelim.Limbook.web.repository;


import com.daelim.Limbook.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //회원가입
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

    public Optional<User> findbyId(String id){

        String sql = "select * from user where user_id = ?";

        List<User> result = jdbcTemplate.query(sql, userRowMapper(), id);

        return result.stream().findAny();
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getString("user_id"));
            user.setPw(rs.getString("user_pw"));
            user.setName(rs.getString("user_name"));
            user.setPhone(rs.getString("user_phone"));
            user.setDepartment(rs.getString("user_department"));
            return user;
        };
    }

}
