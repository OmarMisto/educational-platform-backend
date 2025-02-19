package com.spu.OEP.repository;

import com.spu.OEP.DTO.ViewPlayLists;
import com.spu.OEP.model.CoursePlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface CoursePlayListRepo extends JpaRepository<CoursePlayList,Long> {
    List<CoursePlayList> findAllByInstructorUserId(long instructorId);

//     @Query("select course_id,list_name,last_update,play_list_id from course inner join play_list where instructor_user_id=:instructorId")
//      List<?> findByCoursesInstructorUserId(@Param("instructorId") long instructorId);


}
