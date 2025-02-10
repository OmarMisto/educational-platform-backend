package com.spu.OEP.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RateCourseDTO {
    private long courseId;
    private long userId;
    private float rate;
}
