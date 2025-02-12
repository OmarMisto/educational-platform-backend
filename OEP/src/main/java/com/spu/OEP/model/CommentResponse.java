package com.spu.OEP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentResponseId;
    private String message;
    private final LocalDateTime responseDate=LocalDateTime.now();
    @ManyToOne
    private Comment comment;
    @ManyToOne
    private User user;
}
