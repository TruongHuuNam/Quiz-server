package com.truonghuunam.backend_test__online.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.truonghuunam.backend_test__online.BackendTestOnlineApplication;
import com.truonghuunam.backend_test__online.enums.UserRole;
import com.truonghuunam.backend_test__online.models.User;
import com.truonghuunam.backend_test__online.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService {

    private final BackendTestOnlineApplication backendTestOnlineApplication;

    @Autowired
    private UserRepository userRepository;

    UserServiceImpl(BackendTestOnlineApplication backendTestOnlineApplication) {
        this.backendTestOnlineApplication = backendTestOnlineApplication;
    }

    @PostConstruct
    private void createAdminUser() {
        User optinalUser = userRepository.findByRole(UserRole.ADMIN);
        if (optinalUser == null) {
            User user = new User();

            user.setName("Admin");
            user.setEmail("admin@gmail.com");
            user.setRole(UserRole.ADMIN);
            user.setPassword("truonghuunam");

            userRepository.save(user);
        }
    }

    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email) != null;
    }

    public User createUser(User user) {
        user.setRole(UserRole.USER);
        return userRepository.save(user);
    }

    public User login(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent() && user.getPassword().equals(optionalUser.get().getPassword())) {
            return  optionalUser.get();
        }
        return null; 
    }

}
