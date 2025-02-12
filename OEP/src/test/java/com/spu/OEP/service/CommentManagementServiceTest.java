package com.spu.OEP.service;

import com.spu.OEP.DTO.PostCommentDTO;
import com.spu.OEP.DTO.ResponseToCommentDTO;
import com.spu.OEP.DTO.ViewCommentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentManagementServiceTest {
    @Autowired
    CommentManagementService commentManagementService;
    @Test
    void testTheSuccessOfPostComment(){
        assertEquals("success",commentManagementService.postCommentService(PostCommentDTO.builder().courseId(1).message("Good work").userId(1).build()));
    }
    @Test
    void testTheFailOfPostCommentWithUnExistCourseId(){
        assertEquals("user or course is not found",commentManagementService.postCommentService(PostCommentDTO.builder().courseId(2).message("Good work").userId(1).build()));
    }
    @Test
    void testTheFailOfPostCommentWithUnExistUserId(){
        assertEquals("user or course is not found",commentManagementService.postCommentService(PostCommentDTO.builder().courseId(1).message("Good work").userId(111).build()));
    }
    @Test
    void testTheFailOfPostCommentWithUnExistUserAndCourseId(){
        assertEquals("user or course is not found",commentManagementService.postCommentService(PostCommentDTO.builder().courseId(33).message("Good work").userId(111).build()));
    }
    @Test
    void testTheSuccessOfViewComments(){
        Collection<ViewCommentDTO> viewCommentDTOS= commentManagementService.viewCourseCommentsService(1);
        int count=0;
        for (ViewCommentDTO v:viewCommentDTOS) {
            assertTrue(v.getCommentId() > 0);
            assertEquals(v.getReqResponse(),"success");
            count++;
        }
        assertEquals(count,2);

    }
    @Test
    void testTheSuccessOfDeleteCommentById(){
       assertEquals("success", commentManagementService.deleteCommentService(152));

    }
    @Test
    void testTheSuccessOfSetMostFrequentComment(){
        assertEquals("success",commentManagementService.setAsMostFrequentCommentService(252));
    }
    @Test
    void testTheSuccessOfViewMostFrequentComment(){
       assertEquals( 3, (long) commentManagementService.viewMostFrequentCommentsService(1).size());
    }
    @Test
    void testTheSuccessOfPostResponse(){
        assertEquals("success",commentManagementService.responseToCommentService(ResponseToCommentDTO.builder().commentId(1).userId(1).message("Thanks").build()));
    }
    @Test
    void testTheSuccessOfViewCommentResponses(){
        assertEquals(2,commentManagementService.viewCommentResponsesService(1).size());
    }






}