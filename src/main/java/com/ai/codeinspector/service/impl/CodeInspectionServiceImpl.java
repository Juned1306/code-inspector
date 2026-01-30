package com.ai.codeinspector.service.impl;


import com.ai.codeinspector.dto.CodeInspectionRequestDto;
import com.ai.codeinspector.dto.CodeInspectionResponseDto;
import com.ai.codeinspector.excpetion.AIServiceException;
import com.ai.codeinspector.service.CodeInspectionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CodeInspectionServiceImpl implements CodeInspectionService {


    private final Client client;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public CodeInspectionServiceImpl() {
        // Uses GOOGLE_API_KEY from system environment variable
        this.client = new Client();
    }


    @Override
    public CodeInspectionResponseDto inspectCode(CodeInspectionRequestDto requestDto) {
        CodeInspectionResponseDto responseDto = new CodeInspectionResponseDto();
        String codeSnippet = requestDto.getCode(); // Save original code


        try {
            String systemInstruction = """
                AI System Instruction: Senior Code Reviewer (7+ Years of Experience)


                Role & Responsibilities:
                     You are an expert code reviewer with 7+ years of development experience. Your role is to analyze, review, and improve code written by developers. You focus on:
                                    	•	Code Quality :- Ensuring clean, maintainable, and well-structured code.
                                    	•	Best Practices :- Suggesting industry-standard coding practices.
                                    	•	Efficiency & Performance :- Identifying areas to optimize execution time and resource usage.
                                    	•	Error Detection :- Spotting potential bugs, security risks, and logical flaws.
                                    	•	Scalability :- Advising on how to make code adaptable for future growth.
                                    	•	Readability & Maintainability :- Ensuring that the code is easy to understand and modify.
                    
                                    Guidelines for Review:
                                    	1.	Provide Constructive Feedback :- Be detailed yet concise, explaining why changes are needed.
                                    	2.	Suggest Code Improvements :- Offer refactored versions or alternative approaches when possible.
                                    	3.	Detect & Fix Performance Bottlenecks :- Identify redundant operations or costly computations.
                                    	4.	Ensure Security Compliance :- Look for common vulnerabilities (e.g., SQL injection, XSS, CSRF).
                                    	5.	Promote Consistency :- Ensure uniform formatting, naming conventions, and style guide adherence.
                                    	6.	Follow DRY (Don’t Repeat Yourself) & SOLID Principles :- Reduce code duplication and maintain modular design.
                                    	7.	Identify Unnecessary Complexity :- Recommend simplifications when needed.
                                    	8.	Verify Test Coverage :- Check if proper unit/integration tests exist and suggest improvements.
                                    	9.	Ensure Proper Documentation :- Advise on adding meaningful comments and docstrings.
                                    	10.	Encourage Modern Practices :- Suggest the latest frameworks, libraries, or patterns when beneficial.
                    
                                    Tone & Approach:
                                    	•	Be precise, to the point, and avoid unnecessary fluff.
                                    	•	Provide real-world examples when explaining concepts.
                                    	•	Assume that the developer is competent but always offer room for improvement.
                                    	•	Balance strictness with encouragement :- highlight strengths while pointing out weaknesses.


                Respond ONLY in JSON format with:
                {
                  "errors": [],
                  "suggestions": [],
                  "correctCode": "",
                  "explanation": ""
                }
                """;


            String prompt = systemInstruction + "\n\n Code to review:\n" + codeSnippet;


            GenerateContentResponse response = client.models.generateContent(
                    "gemini-3-flash-preview",
                    prompt,
                    null
            );


            String aiText = response.text();


            // Remove code blocks if AI adds ```json or ```
            aiText = aiText.replaceAll("```json", "")
                    .replaceAll("```", "")
                    .trim();


            // Ensure AI returned valid JSON
            if (!aiText.startsWith("{") || !aiText.endsWith("}")) {
                throw new AIServiceException("AI returned invalid JSON");
            }


            // Parse AI JSON into DTO
            responseDto = objectMapper.readValue(aiText, CodeInspectionResponseDto.class);


            // Add original code so frontend can show diff
            responseDto.setOriginalCode(codeSnippet);


            // Fallback if AI missed any fields
            if (responseDto.getErrors() == null) responseDto.setErrors(List.of());
            if (responseDto.getSuggestions() == null) responseDto.setSuggestions(List.of());
            if (responseDto.getCorrectCode() == null || responseDto.getCorrectCode().isEmpty())
                responseDto.setCorrectCode(codeSnippet);
            if (responseDto.getExplanation() == null || responseDto.getExplanation().isEmpty())
                responseDto.setExplanation("AI could not provide explanation.");


        } catch (Exception e) {
            e.printStackTrace();


            // Fallback in case of any exception
            responseDto.setOriginalCode(codeSnippet);
            responseDto.setErrors(List.of());
            responseDto.setSuggestions(List.of());
            responseDto.setCorrectCode(codeSnippet);
            responseDto.setExplanation("Failed to inspect code: " + e.getMessage());
        }


        return responseDto;
    }
}
