package com.spu.OEP.DTO;

import com.spu.OEP.model.CourseCategories;
import com.spu.OEP.model.CourseLevels;
import com.spu.OEP.model.CourseRequirements;
import com.spu.OEP.model.CourseTools;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Clob;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateCourseDTO {
    private long courseId;
    private long instructorId;
    private String title;
    private String description;
    private CourseLevels level;
    private CourseCategories category;
    private List<String> requirements;
    private List<String> tools;
}
