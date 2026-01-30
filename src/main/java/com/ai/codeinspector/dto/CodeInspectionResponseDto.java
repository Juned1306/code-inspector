package com.ai.codeinspector.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "CodeInspectionResponse",
        description = "AI-generated response containing code issues, suggestions, and fixes"
)
public class CodeInspectionResponseDto {


    @Schema(
            description = "List of errors found in the submitted code",
            example = "[\"Syntax error\",\"Null pointer\"]"
    )
    private List<String> errors;


    @Schema(
            description = "List of improvement or best-practice suggestions",
            example = "[\"Use meaningful variable names\",\"Add full checks\"]"
    )
    private List<String> suggestions;


    @Schema(
            description = "AI-generated version of the submitted code"
    )
    private String correctCode;


    @Schema(
            description = "Human-readable explanation of issues and fixes"
    )
    private String explanation;


    @Schema(
            description = "Original code submitted by the user"
    )
    private String originalCode;
}
