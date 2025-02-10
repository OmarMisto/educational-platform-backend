package com.spu.OEP.repository;

import com.spu.OEP.DTO.CommentResponseDTO;
import com.spu.OEP.model.CommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CommentResponseRepo extends JpaRepository<CommentResponse,Long> {
//    @Query(value = "SELECT new com.spu.OEP.DTO.CommentResponseDTO(message,response_date,email,img_data) FROM comment_response cr" +
//            " INNER JOIN comment c ON cr.comment_comment_id=c.comment_id" +
//            "INNER JOIN user u ON c.user_user_id=u.user_id" +
//            "INNER JOIN account a ON a.account_id=u.account_account_id" +
//            "INNER JOIN user_img uim ON uim.img_id=u.user_img_img_id  ",nativeQuery = true)
@Query("SELECT new com.spu.OEP.DTO.CommentResponseDTO(cr.message, cr.responseDate, a.email, uim.imgData) " +
        "FROM CommentResponse cr " +
        "INNER JOIN cr.comment c " +
        "INNER JOIN c.user u " +
        "INNER JOIN u.account a " +
        "INNER JOIN u.userImg uim " +
        "WHERE c.commentId = :commentId")
    Collection<CommentResponseDTO> findAllByCommentCommentId(@Param("commentId") long commentId);
}
