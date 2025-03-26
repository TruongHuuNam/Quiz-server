package com.truonghuunam.backend_test__online.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truonghuunam.backend_test__online.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    
}
