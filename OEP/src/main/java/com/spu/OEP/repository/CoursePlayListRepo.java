package com.spu.OEP.repository;

import com.spu.OEP.model.CoursePlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePlayListRepo extends JpaRepository<CoursePlayList,Long> {
}
