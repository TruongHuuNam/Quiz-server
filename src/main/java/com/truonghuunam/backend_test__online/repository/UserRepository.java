package com.truonghuunam.backend_test__online.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truonghuunam.backend_test__online.enums.UserRole;
import com.truonghuunam.backend_test__online.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByRole(UserRole role);

    User findFirstByEmail(String email);

    Optional<User> findByEmail(String email);
}
