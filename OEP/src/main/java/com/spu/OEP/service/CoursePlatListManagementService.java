package com.spu.OEP.service;

import com.spu.OEP.DTO.AddCourseToPlayListDTO;
import com.spu.OEP.DTO.CreatePlayListDTO;
import com.spu.OEP.model.Course;
import com.spu.OEP.model.CoursePlayList;
import com.spu.OEP.repository.CoursePlayListRepo;
import com.spu.OEP.repository.CourseRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CoursePlatListManagementService {
    @Autowired
    CoursePlayListRepo coursePlayListRepo;
    @Autowired
    CourseRepo courseRepo;

    public String createPlayListService(CreatePlayListDTO createPlayListDTO) {

        if(createPlayListDTO.getPlayListName()==null||createPlayListDTO.getPlayListName().length()<6){
            return "invalid input";
        }
//        ArrayList<Course> courses=new ArrayList<>();
//        if (createPlayListDTO.getCoursesId()!=null){
//            for (long id :createPlayListDTO.getCoursesId()){
//                courses.add(courseRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Not found")));
//            }
//        }
        try {
            coursePlayListRepo.save(CoursePlayList.builder().listName(createPlayListDTO.getPlayListName()).build());
        }catch (Exception e){
            return "error while saving playlist: "+e.getMessage();
        }
        return "success";
    }

    public String deletePlayList(long playListId) {
        try {
            if(coursePlayListRepo.existsById(playListId)){
                coursePlayListRepo.deleteById(playListId);
//            courseRepo.deleteByCoursePlayListCoursePlayListId(playListId);
                return "success";
            }
        }catch (Exception e){
            return e.getMessage()+"Error while deleting";
        }
        return "fail";
    }

    public String addCourseToPlayList(AddCourseToPlayListDTO addCourseToPlayListDTO) {
        if(addCourseToPlayListDTO.getCourseId()<=0||addCourseToPlayListDTO.getPlaylistId()<=0){
             return "invalid input";
        }
        if (!coursePlayListRepo.existsById(addCourseToPlayListDTO.getPlaylistId())||!courseRepo.existsById(addCourseToPlayListDTO.getCourseId())){
            return "the playlist or course is not found";
        }
        try {
            courseRepo.updateByCoursePlayListId(addCourseToPlayListDTO.getCourseId(),addCourseToPlayListDTO.getPlaylistId());
            return "success";
        }catch (Exception e){
            return "error while adding the course"+e.getMessage();
        }
    }
    public String removeCourseFromPlayList(long courseId) {
        if(courseId<=0){
            return "invalid playlist";
        }
        try {
            if (!courseRepo.existsById(courseId))return "the course is not found";
            courseRepo.removeCourseFormPlayList(courseId);
            return "success";
        }catch (Exception e){
            return e.getMessage().concat(" error while removing");
        }

    }
}
