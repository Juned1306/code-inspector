package com.ai.codeinspector.service;

import com.ai.codeinspector.dto.CodeInspectionRequestDto;
import com.ai.codeinspector.dto.CodeInspectionResponseDto;

public interface CodeInspectionService{
    CodeInspectionResponseDto inspectCode(CodeInspectionRequestDto request);
}
