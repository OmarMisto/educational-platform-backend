package com.spu.OEP.service;

import com.spu.OEP.DTO.PostCourseDTO;
import com.spu.OEP.DTO.RateCourseDTO;
import com.spu.OEP.DTO.ShowCourseDTO;
import com.spu.OEP.DTO.UploadImgDTO;
import com.spu.OEP.model.*;
import com.spu.OEP.repository.CategoryRepo;
import com.spu.OEP.repository.CourseRepo;
import com.spu.OEP.repository.InstructorRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
@SpringBootTest
public class CourseServiceTest {
    @Autowired
    CourseManagementService courseManagementService;
    @Test
    void testTheSuccessOfPostCourseService(){
       List<String> tools=new ArrayList<>();
        tools.add("VSCode");
        tools.add("C++ Compiler");
        List<String> requirements=new ArrayList<>();
        requirements.add("Any thing");
        requirements.add("C++");
        assertEquals("success", courseManagementService.postCourseService(PostCourseDTO.builder().title("C++ Full Course")
                .level(CourseLevels.Advanced)
                .description("Learning C++ Programming language Advanced topics")
                .instructorId(1)
                .category(CourseCategories.builder().categoryTitle("Programming").build())
                .tools(tools)
                .requirements(requirements).build()));
    }
    @Test
    void testTheFailureOfPostCourseServiceWithUnExitInstructor(){
        ArrayList<String> tools=new ArrayList<>();
        tools.add("VSCode");
        tools.add("C++ Compiler");
        ArrayList<String> requirements=new ArrayList<>();
        requirements.add("Any thing");
        requirements.add("C++");
        assertEquals("fail", courseManagementService.postCourseService(PostCourseDTO.builder().title("C++ Full Course")
                .level(CourseLevels.Advanced)
                .description("Learning C++ Programming language Advanced topics")
                .instructorId(10)
                .category(CourseCategories.builder().categoryTitle("Programming").build())
                .tools(tools)
                .requirements(requirements).build()));
    }
    @Test
    void testTheSuccessOfDeleteCourseService(){
        assertEquals("success",courseManagementService.deletePostedCourseService(1));
    }
    @Test
    void testTheSuccessOfRateCourse(){
        RateCourseDTO rateCourseDTO= RateCourseDTO.builder().courseId(1).userId(1).rate(5).build();
        assertEquals("success",courseManagementService.rateCourseService(rateCourseDTO));
    }
    @Test
    void testTheSuccessOfDisplayCourseRate(){
        assertNotEquals("success",courseManagementService.displayCourseRateService(1));
    }
    @Test
    void testTheSuccessOfDisplayCoursesCategory(){
        assertNotEquals("success",courseManagementService.showCourseCategories());
    }
    @Test
    void testTheSuccessOfShowCourse(){
        assertEquals("success",courseManagementService.showCourseService(1).getRequestMessage());
    }





}