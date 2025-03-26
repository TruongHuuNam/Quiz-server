package com.truonghuunam.backend_test__online.service.test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.truonghuunam.backend_test__online.BackendTestOnlineApplication;
import com.truonghuunam.backend_test__online.dto.QuestionDTO;
import com.truonghuunam.backend_test__online.dto.QuestionResponse;
import com.truonghuunam.backend_test__online.dto.SubmitTestDTO;
import com.truonghuunam.backend_test__online.dto.TestDTO;
import com.truonghuunam.backend_test__online.dto.TestDetailsDTO;
import com.truonghuunam.backend_test__online.dto.TestResultDTO;
import com.truonghuunam.backend_test__online.models.Question;
import com.truonghuunam.backend_test__online.models.Test;
import com.truonghuunam.backend_test__online.models.TestResult;
import com.truonghuunam.backend_test__online.models.User;
import com.truonghuunam.backend_test__online.repository.QuestionRepository;
import com.truonghuunam.backend_test__online.repository.TestRepository;
import com.truonghuunam.backend_test__online.repository.TestResultRepository;
import com.truonghuunam.backend_test__online.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private UserRepository userRepository;

    public TestDTO createTest(TestDTO testDTO) {
        Test test = new Test();

        test.setTitle(testDTO.getTitle());
        test.setDescription(testDTO.getDescription());
        test.setTime(testDTO.getTime());

        return testRepository.save(test).getDto();
    }

    public QuestionDTO addQuestionInTest(QuestionDTO dto) {
        Optional<Test> optionalTest = testRepository.findById(dto.getId());
        if (optionalTest.isPresent()) {
            Question question = new Question();

            question.setTest(optionalTest.get());
            question.setQuestionText(dto.getQuestionText());
            question.setOptionA(dto.getOptionA());
            question.setOptionB(dto.getOptionB());
            question.setOptionC(dto.getOptionC());
            question.setOptionD(dto.getOptionD());
            question.setCorrectOption(dto.getCorrectOption());

            return questionRepository.save(question).getDto();
        }
        throw new EntityNotFoundException();
    }

    public List<TestDTO> getAllTests() {
        return testRepository.findAll().stream()
                .peek(test -> test.setTime(test.getQuestions().size() * test.getTime())).toList()
                .stream().map(Test::getDto).collect(Collectors.toList());
    }

    public TestDetailsDTO getAllQuestionsByTest(Long id) {
        Optional<Test> optionalTest = testRepository.findById(id);
        TestDetailsDTO testDetailsDTO = new TestDetailsDTO();
        if (optionalTest.isPresent()) {
            TestDTO testDTO = optionalTest.get().getDto();
            testDTO.setTime(optionalTest.get().getTime() * optionalTest.get().getQuestions().size());
            testDetailsDTO.setTestDTO(testDTO);
            testDetailsDTO.setQuestions(optionalTest.get().getQuestions().stream().map(Question::getDto).toList());
            return testDetailsDTO;
        }
        return testDetailsDTO;
    }

    public TestResultDTO submitTest(SubmitTestDTO request) {
        Test test = testRepository.findById(request.getTestId())
                .orElseThrow(() -> new EntityNotFoundException("Test not found"));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        int correctAnswer = 0;
        for (QuestionResponse response : request.getResponses()) {
            Question question = questionRepository.findById(response.getQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException("Question not found"));
            if (question.getCorrectOption().equals(response.getSelectedOption())) {
                correctAnswer++;
            }
        }
        int totalQuestions = test.getQuestions().size();
        double percentage = ((double) correctAnswer / totalQuestions) * 100;
        TestResult testResult = new TestResult();
        testResult.setTest(test);
        testResult.setUser(user);
        testResult.setTotalQuestions(totalQuestions);
        testResult.setCorrectAnswers(correctAnswer);
        testResult.setPercentage(percentage);

        return testResultRepository.save(testResult).getDto();
    }

    public List<TestResultDTO> getAllTestResults() {
        return testResultRepository.findAll().stream().map(TestResult::getDto).collect(Collectors.toList());
    }

    public List<TestResultDTO> getAllTestResultsOfUser(Long userId) {
        return testResultRepository.findAllByUserId(userId).stream().map(TestResult::getDto)
                .collect(Collectors.toList());
    }
}
