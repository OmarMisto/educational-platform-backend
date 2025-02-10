package com.spu.OEP.controller;
import com.spu.OEP.DTO.*;
import com.spu.OEP.service.CourseManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("course/management")
public class CourseManagementController {
    @Autowired
    CourseManagementService courseManagementService;
    @PostMapping("post/course")
    public ResponseEntity<String> postCourse(@RequestPart("courseData")PostCourseDTO postCourseDTO){
        return ResponseEntity.ok(courseManagementService.postCourseService(postCourseDTO));
    }
    @PostMapping(path = "upload/video", consumes =MediaType.ALL_VALUE )
    public ResponseEntity<String>uploadVideo(@RequestPart("courseId")long courseId,@RequestPart("video") MultipartFile video) throws IOException {
        return ResponseEntity.ok(courseManagementService.uploadVideoService(courseId,video));
    }
    @DeleteMapping("delete/posted/course")
    public ResponseEntity<String>deletePostedCourse(@RequestParam("courseId")long courseId){
        return ResponseEntity.ok(courseManagementService.deletePostedCourseService(courseId));

    }
    @PostMapping("update/course")
    public ResponseEntity<String>UpdateCourse(@RequestPart("courseData") UpdateCourseDTO updateCourseDTO) throws IOException {
        return ResponseEntity.ok(courseManagementService.updateCourseService(updateCourseDTO));
    }
    @PostMapping("rate/course")
    public ResponseEntity<String> rateCourse(@RequestBody RateCourseDTO rateCourseDTO){
        return ResponseEntity.ok(courseManagementService.rateCourseService(rateCourseDTO));
    }
    @GetMapping("display/course/rate")
    public ResponseEntity<DisplayRateDTO>displayCourseRate(@RequestParam("courseId")long courseId){
        return ResponseEntity.ok(courseManagementService.displayCourseRateService(courseId));
    }
    @GetMapping("show/course/categories")
    public ResponseEntity<List<Map<String,String>>> showCourseCategories(){
        return ResponseEntity.ok(courseManagementService.showCourseCategories());
    }
    @GetMapping("show/course")
    public ResponseEntity<ShowCourseDTO> showCourse(@RequestParam("courseId")long courseId){
        return ResponseEntity.ok(courseManagementService.showCourseService(courseId));
    }
    @GetMapping("playCourse/{courseId}")
    public ResponseEntity<?>playCourse(@PathVariable("courseId") long courseId){
        return ResponseEntity.ok(courseManagementService.playCourseService(courseId));
    }
    
}
