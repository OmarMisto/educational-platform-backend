package com.spu.OEP.repository;

import com.spu.OEP.model.CourseTools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseToolsRepo extends JpaRepository<CourseTools,Long> {
}
