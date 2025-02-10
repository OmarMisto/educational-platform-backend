package com.spu.OEP.controller;

import com.spu.OEP.DTO.*;
import com.spu.OEP.service.CommentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("comment/management")
public class CommentManagementController {
    @Autowired
    CommentManagementService commentManagementService;
    @PostMapping("postComment")
    public ResponseEntity<String>postCommentController(@RequestBody PostCommentDTO postCommentDTO){
        return ResponseEntity.ok(commentManagementService.postCommentService(postCommentDTO));
    }
    @GetMapping("view/comments")
    public ResponseEntity<?>viewCourseCommentsController(@RequestParam("courseId")long courseId){
        return ResponseEntity.ok(commentManagementService.viewCourseCommentsService(courseId));
    }
    @PostMapping("edit/comment")
    public ResponseEntity<PostedCommentDTO>editCommentController(@RequestBody EditCommentDTO editCommentDTO) throws IOException {
        return ResponseEntity.ok(commentManagementService.editCommentService(editCommentDTO));
    }
    @DeleteMapping("delete/comment")
    public ResponseEntity<String>deleteCommentController(@RequestParam("commentId") long commentId){
        return ResponseEntity.ok(commentManagementService.deleteCommentService(commentId));
    }
    @PostMapping("set/most/frequent")
    public ResponseEntity<String>setAsMostFrequentComment(@RequestParam("commentId")long commentId){
        return ResponseEntity.ok(commentManagementService.setAsMostFrequentCommentService(commentId));

    }
    @GetMapping("view/most/frequent/comments")
    public ResponseEntity<?>viewMostFrequentCommentsController(@RequestParam("courseId")long courseId){
        return ResponseEntity.ok(commentManagementService.viewMostFrequentCommentsService(courseId));
    }
    @PostMapping("response/to/comment")
    public ResponseEntity<String>responseToCommentController(@RequestBody ResponseToCommentDTO responseToCommentDTO){
        return ResponseEntity.ok(commentManagementService.responseToCommentService(responseToCommentDTO));

    }
    @GetMapping("view/comment/response")
    public ResponseEntity<Collection<CommentResponseDTO>>viewCommentResponseController(@RequestParam("commentId")long commentId){
        return ResponseEntity.ok(commentManagementService.viewCommentResponsesService(commentId));
    }

}
