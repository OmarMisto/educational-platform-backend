package com.spu.OEP.repository;

import com.spu.OEP.model.CourseRequirements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRequirementsRepo extends JpaRepository<CourseRequirements,Long> {
}
