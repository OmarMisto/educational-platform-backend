package com.spu.OEP.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class ShowPostedCourseDTO {
    private long courseId;
    private String title;
    private float rate;
    private String category;
    private byte[] contentImg;
    private String requestMessage;
    private LocalDateTime publishDate;
}
