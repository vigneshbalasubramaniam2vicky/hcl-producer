package com.example.paymentingestor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;
    private String path;
    private List<Violation> violations;

    @Data
    @AllArgsConstructor
    public static class Violation {
        private String field;
        private String message;
    }
}
