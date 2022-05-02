package com.daelim.Limbook.web.repository;

import com.daelim.Limbook.domain.User;

import java.util.Optional;

public interface UserRepository {

    public User save(User user);

    public Optional<User> findbyId(String id);

}
