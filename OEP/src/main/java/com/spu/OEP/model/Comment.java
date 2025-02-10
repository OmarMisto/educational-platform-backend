package com.spu.OEP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentId;
    private boolean mostFrequentCommentCheck=false;
    private String message;
    private final LocalDateTime postedDate=LocalDateTime.now();
    private LocalDateTime modifiedAt;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "commentResponseId")
    private Collection<CommentResponse> commentResponses;
    @ManyToOne
    private Course course;
}
