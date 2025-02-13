package com.spu.OEP.service;

import com.spu.OEP.DTO.AddCourseToPlayListDTO;
import com.spu.OEP.DTO.CreatePlayListDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CoursePlatListManagementServiceTest {
    @Autowired
    CoursePlatListManagementService coursePlatListManagementService;
    @Test
    void testTheSuccessOfCreatePlayList(){
       assertEquals("success",coursePlatListManagementService.createPlayListService(CreatePlayListDTO.builder().playListName("C++ Full PlayList").build()));
    }
    @Test
    void testTheSuccessOfDeletingPlayList(){
        assertEquals("success",coursePlatListManagementService.deletePlayList(3));
    }
    @Test
    void testTheSuccessOfAddCourse(){
        assertEquals("success",coursePlatListManagementService.addCourseToPlayList(AddCourseToPlayListDTO.builder().courseId(2).playlistId(4).build()));
    }
    @Test
    void testTheSuccessOfRemoveCourse(){
        assertEquals("success",coursePlatListManagementService.removeCourseFromPlayList(2));
    }


}