package com.truonghuunam.backend_test__online.dto;

import java.util.List;

import lombok.Data;

@Data
public class SubmitTestDTO {
    private Long testId;

    private Long userId;

    private List<QuestionResponse> responses;
}
