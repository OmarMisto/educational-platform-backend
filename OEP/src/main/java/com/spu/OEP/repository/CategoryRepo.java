package com.spu.OEP.repository;

import com.spu.OEP.model.CourseCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<CourseCategories,Long> {
    boolean existsByCategoryTitle(String categoryTitle);
    CourseCategories findByCategoryTitle(String categoryTitle);



    CourseCategories findByCategoryId(long categoryId);
}
