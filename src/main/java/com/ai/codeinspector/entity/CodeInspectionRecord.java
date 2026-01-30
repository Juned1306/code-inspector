package com.ai.codeinspector.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "code_inspections")

public class CodeInspectionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String code; // original user code

    @Column(columnDefinition = "TEXT")
    private String correctedCode; // AI corrected code

    @Column(columnDefinition = "TEXT")
    private String errors; // stored as JSON string

    @Column(columnDefinition = "TEXT")
    private String suggestions; // stored as JSON string

    @Column(columnDefinition = "TEXT")
    private String explanation; // human-readable explanation

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
