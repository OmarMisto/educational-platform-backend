package com.spu.OEP.repository;

import com.spu.OEP.model.CourseCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseCategoriesRepo extends JpaRepository<CourseCategories,Long> {
}
