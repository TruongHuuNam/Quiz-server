package com.truonghuunam.backend_test__online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truonghuunam.backend_test__online.models.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

}
