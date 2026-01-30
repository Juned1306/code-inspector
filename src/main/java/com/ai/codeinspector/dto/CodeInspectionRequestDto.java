package com.ai.codeinspector.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "CodeInspectionRequest",
        description = "Request object containing source code and language for inspection"
)
public class CodeInspectionRequestDto {

    @NotBlank(message = "Source code cannot be empty")
    @Schema(
            description = "Source code pasted by user. Programmig language is auto-detected by AI",
            example = "public class test{public static void main(String []args]{} }",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String code;
}
