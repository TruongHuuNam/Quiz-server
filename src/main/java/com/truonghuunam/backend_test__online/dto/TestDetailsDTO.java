package com.truonghuunam.backend_test__online.dto;

import java.util.List;

import lombok.Data;

@Data
public class TestDetailsDTO {
    private TestDTO testDTO;
    private List<QuestionDTO> questions;
}
