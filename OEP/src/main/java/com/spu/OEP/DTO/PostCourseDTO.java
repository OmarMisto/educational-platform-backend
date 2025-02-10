package com.spu.OEP.DTO;

import com.spu.OEP.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.sql.Clob;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostCourseDTO {
    private long instructorId;
    private String title;
    private String description;
    private CourseLevels level;
    private CourseCategories category;
    private List<String> requirements;
    private List<String> tools;

}
