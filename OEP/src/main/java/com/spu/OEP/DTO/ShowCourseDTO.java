package com.spu.OEP.DTO;

import com.spu.OEP.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowCourseDTO {
    private long courseId;
    private String title;
    private String description;
    private LocalDateTime publishDate;
    private DisplayRateDTO courseRate;
    private long instructorId;
    private String instructorFullName;
    private byte[] instructorImage;
    private CourseLevels level;
    private String category;
    private long playListId;
    private String requestMessage;

}
