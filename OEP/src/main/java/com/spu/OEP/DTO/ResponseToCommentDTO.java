package com.spu.OEP.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseToCommentDTO {
    private long commentId;
    private long userId;
    private String message;
}
