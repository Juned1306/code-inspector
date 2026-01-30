package com.ai.codeinspector.controller;

import com.ai.codeinspector.dto.CodeInspectionRequestDto;
import com.ai.codeinspector.dto.CodeInspectionResponseDto;
import com.ai.codeinspector.service.CodeInspectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/code")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(
        name = "AI Code Inspector",
        description = "APIs for inspecting and improving source code using Gemini AI"
)
public class CodeInspectionController {

    private final CodeInspectionService codeInspectionService;

    public CodeInspectionController(CodeInspectionService codeInspectionService) {
        this.codeInspectionService = codeInspectionService;
    }

    @Operation(
            summary = "Inspect source code",
            description = "Analyzes source code, detects issues, and suggests improvements using AI"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Code inspected successfully",
                    content = @Content(schema = @Schema(implementation = CodeInspectionResponseDto.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<CodeInspectionResponseDto> inspectCode(
            @Valid @RequestBody CodeInspectionRequestDto requestDto
    ) {
        CodeInspectionResponseDto response = codeInspectionService.inspectCode(requestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
