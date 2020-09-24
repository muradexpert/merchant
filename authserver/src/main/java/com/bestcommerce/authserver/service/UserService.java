package com.bestcommerce.authserver.service;

import com.bestcommerce.authserver.entity.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> getUserWithAuthorities();

}
