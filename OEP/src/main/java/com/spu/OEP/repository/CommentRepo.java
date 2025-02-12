package com.spu.OEP.repository;

import com.spu.OEP.DTO.PostCommentDTO;
import com.spu.OEP.DTO.PostedCommentDTO;
import com.spu.OEP.DTO.ViewCourseCommentsDTO;
import com.spu.OEP.model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {
    @Query(value = "select a.email,uim.img_data,cm.message from" +
            "course c inner join comment cm on c.course_id=cm.course_course_id" +
            "inner join user u on cm.user_user_id=u.user_id" +
            "inner join account a on a.account_id=u.account_account_id " +
            "inner join user_img uim on u.user_img_img_id=uim.img_id)" +
            "where c.course_id=:courseId;",nativeQuery = true)
    Collection<?> getCommentsByCourseId(@Param("courseId") long courseId);
    @Transactional
    @Modifying
    @Query("update Comment c set c.message=:message,c.modifiedAt=:modifiedAt where c.commentId=:commentId")
    void updateById(@Param("commentId") long commentId,@Param("message") String message,@Param("modifiedAt") LocalDateTime modifiedAt);
    @Query("select new com.spu.OEP.DTO.PostedCommentDTO(message,modifiedAt,postedDate) from Comment c where c.commentId=:commentId ")
    PostedCommentDTO getUpdatedCommentById(@Param("commentId") long commentId);
    @Transactional
    @Modifying
    @Query("update Comment c set c.mostFrequentCommentCheck=true where c.commentId=:commentId")
    void updateCommentById(@Param("commentId") long commentId);
    @Query(value = "select new com.spu.OEP.DTO.ViewCourseCommentsDTO(a.email, uim.img_data, cm.message) from" +
            "course c inner join comment cm on c.course_id=cm.course_course_id" +
            "inner join user u on cm.user_user_id=u.user_id" +
            "inner join account a on a.account_id=u.account_account_id " +
            "inner join user_img uim on u.user_img_img_id=uim.img_id)" +
            "where c.course_id=:courseId and cm.most_frequent_comment_check=1;",nativeQuery = true)
    Collection<ViewCourseCommentsDTO> getCommentsByMostFrequentComments(@Param("courseId") long courseId);


    Collection<Comment> findAllByCourseCourseId(long courseId);
    @Query("select c from Comment c where c.mostFrequentCommentCheck=true and  c.course.courseId=:courseId")
    Collection<Comment> findAllByCourseCourseIdAndIsMostFrequent(@Param("courseId") long courseId);
}
