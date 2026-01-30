package com.ai.codeinspector.controller;

import com.ai.codeinspector.dto.CodeInspectionResponseDto;
import com.ai.codeinspector.entity.CodeInspectionRecord;
import com.ai.codeinspector.repository.CodeInspectionRecordRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "http://localhost:3000")
public class HistoryController {

    private final CodeInspectionRecordRepository repository;
    private final ObjectMapper objectMapper;

    public HistoryController(CodeInspectionRecordRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Operation(summary = "Get all past code inspections")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "History fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<List<CodeInspectionResponseDto>> getAllHistory() {

        List<CodeInspectionRecord> records = repository.findAll();

        List<CodeInspectionResponseDto> history = records.stream().map(record ->
                new CodeInspectionResponseDto(
                        deserialize(record.getErrors()),
                        deserialize(record.getSuggestions()),
                        record.getCorrectedCode(),
                        record.getExplanation(),
                        record.getCode()
                )
        ).toList();

        return ResponseEntity.ok(history);
    }

    private List<String> deserialize(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            return List.of();
        }
    }
}
