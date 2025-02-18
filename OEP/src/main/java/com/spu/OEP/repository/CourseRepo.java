package com.spu.OEP.repository;

import com.spu.OEP.DTO.ShowCourseDTO;
import com.spu.OEP.model.Course;
import com.spu.OEP.model.Video;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {

//    void deleteByCoursePlayListCoursePlayListId(long playListId);
    @Transactional
    @Modifying
    @Query(  value = "UPDATE course SET course_play_list_course_play_list_id = :playListId WHERE course_id = :courseId",
            nativeQuery = true)
    void updateByCoursePlayListId(@Param("courseId") long courseId,@Param("playListId") long playList);
    @Transactional
    @Modifying
    @Query(value = "update course c set c.course_play_list_course_play_list_id=null where c.course_id=:courseId",nativeQuery = true )
    void removeCourseFormPlayList(@Param("courseId") long courseId);

    @Query(value = "select video_data from video where video_id=(select video_video_id from course where course_id=:courseId)",nativeQuery = true)
    byte[] getByCourseId(@Param("courseId") long courseId);
    @Transactional
    @Modifying
    @Query("update Course c set c.video=:video where c.courseId=:courseId")
    void updateCourseVideo(@Param("courseId") long courseId,@Param("video") Video video);
    @Query("select video.videoId from Course c where c.courseId=:courseId")
    Long getVideoVideoIdById(@Param("courseId") long courseId);

    List<Course> findByInstructorUserId(long userId);

//    Course findByInstructorInstructorId(long instructorId);
//    @Query("select * from Course c where c.Instructor.UserId=:instructorId")

}
