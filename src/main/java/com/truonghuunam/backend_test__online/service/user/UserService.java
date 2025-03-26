package com.truonghuunam.backend_test__online.service.user;

import java.util.Optional;

import com.truonghuunam.backend_test__online.models.User;

public interface UserService {
    User createUser(User user);

    Boolean hasUserWithEmail(String email);

    User login(User user);
}
