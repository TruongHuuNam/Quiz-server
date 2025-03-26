package com.truonghuunam.backend_test__online.service.test;

import java.util.List;

import com.truonghuunam.backend_test__online.dto.QuestionDTO;
import com.truonghuunam.backend_test__online.dto.SubmitTestDTO;
import com.truonghuunam.backend_test__online.dto.TestDTO;
import com.truonghuunam.backend_test__online.dto.TestDetailsDTO;
import com.truonghuunam.backend_test__online.dto.TestResultDTO;

public interface TestService {
    TestDTO createTest(TestDTO testDTO);

    QuestionDTO addQuestionInTest(QuestionDTO dto);

    List<TestDTO> getAllTests();

    TestDetailsDTO getAllQuestionsByTest(Long id);

    TestResultDTO submitTest(SubmitTestDTO request);

    List<TestResultDTO> getAllTestResults();

    List<TestResultDTO> getAllTestResultsOfUser(Long userId);
}
