package com.truonghuunam.backend_test__online.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truonghuunam.backend_test__online.models.TestResult;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    List<TestResult> findAllByUserId(Long userId);
}
