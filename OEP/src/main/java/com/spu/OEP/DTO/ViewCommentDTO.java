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
public class ViewCommentDTO {
    private long commentId=0;
    private long userId=0;
    private String userEmail=null;
    private byte[] userImg=null;
    private String message=null;
    private LocalDateTime publishDate=null;
    private LocalDateTime modifiedAt=null;
    private boolean frequentFlag=false;
    private String reqResponse=null;

}
