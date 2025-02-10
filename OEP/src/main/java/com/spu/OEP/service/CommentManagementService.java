package com.spu.OEP.service;

import com.spu.OEP.DTO.*;
import com.spu.OEP.model.Comment;
import com.spu.OEP.model.CommentResponse;
import com.spu.OEP.model.Course;
import com.spu.OEP.model.User;
import com.spu.OEP.repository.CommentRepo;
import com.spu.OEP.repository.CommentResponseRepo;
import com.spu.OEP.repository.CourseRepo;
import com.spu.OEP.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

@Service
public class CommentManagementService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    CommentResponseRepo commentResponseRepo;
    public String postCommentService(PostCommentDTO postCommentDTO) {
        if(postCommentDTO.getUserId()<=0||postCommentDTO.getCourseId()<=0){
            return "Invalid userId or courseId";
        }
        if (!userRepo.existsById(postCommentDTO.getUserId())||!courseRepo.existsById(postCommentDTO.getCourseId())){
            return "user or course is not found";
        }
        try {
            commentRepo.save(Comment.builder()
                    .message(postCommentDTO.getMessage())
                    .user(userRepo.findById(postCommentDTO.getUserId()).orElse(new User()))
                    .course(courseRepo.findById(postCommentDTO.getCourseId()).orElse(new Course()))
                    .build());
            return "success";

        }catch (Exception e){
            return "Error while posting comment "+e.getMessage();
        }

    }

    public Collection<?> viewCourseCommentsService(long courseId) {
        ArrayList<Object> response=new ArrayList<>();
        if(courseId<=0){
            response.add("Invalid course");
            return response;
        }
        if(!courseRepo.existsById(courseId)){
            response.add("the course is not exit");
            return response;
        }
        response.addAll(commentRepo.getCommentsByCourseId(courseId));
        return response;
    }

    public PostedCommentDTO editCommentService(EditCommentDTO editCommentDTO) throws IOException {
        if(editCommentDTO.getCommentId()<=0 ||editCommentDTO.getMessage()==null){
            throw new IllegalArgumentException("invalid request");
        }
        if(!commentRepo.existsById(editCommentDTO.getCommentId())){
            throw new EntityNotFoundException("Entity not found");
        }
        commentRepo.updateById(editCommentDTO.getCommentId(),editCommentDTO.getMessage(),editCommentDTO.getModifiedAt());
       return commentRepo.getUpdatedCommentById(editCommentDTO.getCommentId());
    }

    public String deleteCommentService(long commentId) {
        if(commentId<=0){
            throw new IllegalArgumentException("invalid request");
        }
        if(!commentRepo.existsById(commentId)){
            throw new EntityNotFoundException("Entity not found");
        }
        commentRepo.deleteById(commentId);
        return "success";
    }

    public String setAsMostFrequentCommentService(long commentId) {
        if(commentId<=0){
            throw new IllegalArgumentException("invalid request");
        }
        if(!commentRepo.existsById(commentId)){
            throw new EntityNotFoundException("Entity not found");
        }
        commentRepo.updateCommentById(commentId);
        return "success";
    }

    public Collection<?> viewMostFrequentCommentsService(long courseId) {
        ArrayList<Object> response=new ArrayList<>();
        if(courseId<=0){
            response.add("Invalid course");
            return response;
        }
        if(!courseRepo.existsById(courseId)){
            response.add("the course is not exit");
            return response;
        }
        return commentRepo.getCommentsByMostFrequentComments(courseId);
    }


    public String responseToCommentService(ResponseToCommentDTO responseToCommentDTO) {
        if (responseToCommentDTO.getCommentId()<=0){
            return "invalid comment";
        }
        if(!commentRepo.existsById(responseToCommentDTO.getCommentId())){
            return "comment not found";
        }
        commentResponseRepo.save(CommentResponse.builder().message(responseToCommentDTO.getMessage()).comment(commentRepo.findById(responseToCommentDTO.getCommentId()).orElseThrow(()->new EntityNotFoundException("not found"))).build());
        return "success";
    }

    public Collection<CommentResponseDTO> viewCommentResponsesService(long commentId) {
        ArrayList<Object> response=new ArrayList<>();
        if (commentId<=0){
            throw new IllegalArgumentException("invalid input");
        }
        if (!commentRepo.existsById(commentId)){
            throw new EntityNotFoundException("entity not found");
        }
        return commentResponseRepo.findAllByCommentCommentId(commentId);
    }
}
