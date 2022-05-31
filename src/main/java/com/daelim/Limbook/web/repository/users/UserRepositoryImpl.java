package com.daelim.Limbook.web.repository.users;

import com.daelim.Limbook.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //회원가입
    public User save(User user){

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user");

        Map<String, Object> params = new HashMap<>();

        params.put("user_id", user.getUser_id());
        params.put("user_pw", user.getUser_pw());
        params.put("user_email", user.getUser_email());

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
            user.setUser_id(rs.getString("user_id"));
            user.setUser_pw(rs.getString("user_pw"));
            user.setUser_email(rs.getString("user_email"));

            return user;
        };
    }

}
