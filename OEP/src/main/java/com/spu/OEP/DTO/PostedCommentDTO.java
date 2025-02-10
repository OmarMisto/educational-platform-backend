package com.spu.OEP.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostedCommentDTO {
    private String message;
    private LocalDateTime modifiedAt;
    private LocalDateTime postedAt;
}
