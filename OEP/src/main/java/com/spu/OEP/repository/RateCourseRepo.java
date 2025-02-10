package com.spu.OEP.repository;

import com.spu.OEP.model.CourseRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateCourseRepo extends JpaRepository<CourseRate,Long> {

    CourseRate findByCourseCourseId(long courseId);
}
