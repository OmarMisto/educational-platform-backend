package com.spu.OEP.service;
import com.spu.OEP.DTO.*;
import com.spu.OEP.model.*;
import com.spu.OEP.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
public class CourseManagementService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    CourseRequirementsRepo courseRequirementsRepo;
    @Autowired
    CourseToolsRepo courseToolsRepo;
    @Autowired
    ApplicationContext context;
    @Autowired
    RateCourseRepo rateCourseRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CourseCategoriesRepo courseCategoriesRepo;
    public String postCourseService(PostCourseDTO postCourseDTO)  {
        if(postCourseDTO==null||postCourseDTO.getInstructorId()<=0||postCourseDTO.getTitle()==null){
            return "invalid input";
        }
        if(context.getBean(InstructorRepo.class).existsById(postCourseDTO.getInstructorId())){
            CourseCategories courseCategories=categoryRepo.existsByCategoryTitle(postCourseDTO.getCategory().getCategoryTitle())?postCourseDTO.getCategory():categoryRepo.save(postCourseDTO.getCategory());
            try{
                Course course= courseRepo.save(Course.builder()
                        .title(postCourseDTO.getTitle())
                        .description(postCourseDTO.getDescription())
                        .courseLevel(postCourseDTO.getLevel())
                        .instructor((Instructor) context.getBean(InstructorRepo.class).findById(postCourseDTO.getInstructorId()).orElse(new Instructor()))
                        .courseCategories(categoryRepo.findByCategoryTitle(courseCategories.getCategoryTitle()))
                        .build());
                 courseRequirementsRepo.save(CourseRequirements.builder().requirements(postCourseDTO.getRequirements()).course(course).build());
                 courseToolsRepo.save(CourseTools.builder().tools(postCourseDTO.getTools()).course(course).build());
                 return "success";
            }catch (Exception e){
                return "Error while saving:"+e.getMessage();
            }
        }
        return "fail";
    }
    public String deletePostedCourseService(long courseId) {
        if(courseRepo.existsById(courseId)){
            try{
                courseRepo.deleteById(courseId);
                return "success";
            }catch (Exception e){
                return "Error while deleting course: "+e.getMessage();
            }

        }
        return "fail";
    }
    public String updateCourseService(UpdateCourseDTO updateCourseDTO) throws IOException {
        if(updateCourseDTO==null||updateCourseDTO.getCourseId()<=0||
                updateCourseDTO.getTitle() == null||updateCourseDTO.getCategory()==null||updateCourseDTO.getInstructorId()<=0){
            return "invalid input";
        }
        if(!courseRepo.existsById(updateCourseDTO.getCourseId())){
            return "course is not found";
        }
        try {
            deletePostedCourseService(updateCourseDTO.getCourseId());
            postCourseService(PostCourseDTO.builder().instructorId(updateCourseDTO.getInstructorId())
                    .title(updateCourseDTO.getTitle()).description(updateCourseDTO.getDescription()).level(updateCourseDTO.getLevel()).tools(updateCourseDTO.getTools()).category(updateCourseDTO.getCategory()).
                    requirements(updateCourseDTO.getRequirements())
                    .build());
        } catch (Exception e){
            return "Error while updating: "+e.getMessage();
        }
        return "success";
    }
    public String rateCourseService(RateCourseDTO rateCourseDTO) {
        if(rateCourseDTO.getCourseId()<=0||rateCourseDTO.getUserId()<=0){
            return "invalid input";
        }
        if(context.getBean(AccountRepo.class).existsById(rateCourseDTO.getUserId())&&courseRepo.existsById(rateCourseDTO.getCourseId())){
            CourseRate rate= rateCourseRepo.findByCourseCourseId(rateCourseDTO.getCourseId());
            if(rate==null){
                rateCourseRepo.save(CourseRate.builder().course(courseRepo.findById(rateCourseDTO.getCourseId()).orElse(null))
                        .rate(rateCourseDTO.getRate()).numberOfRatedStudents(1).build());
            }
            else if (rate.getNumberOfRatedStudents()>0){
                rate.setNumberOfRatedStudents(rate.getNumberOfRatedStudents()+1);
                rate.setRate((((rate.getRate())+(rateCourseDTO.getRate()))/rate.getNumberOfRatedStudents()));//todo
                rate.setCourse(courseRepo.findById(rateCourseDTO.getCourseId()).orElse(new Course()));
                rateCourseRepo.save(rate);
            }
            else {
                return "course not found";
            }
            return "success";
        }
        return "fail";
    }
    public DisplayRateDTO displayCourseRateService(long courseId){
        if(courseId<=0){
            return new DisplayRateDTO();
        }try {
            CourseRate courseRate=rateCourseRepo.findByCourseCourseId(courseId);
            return DisplayRateDTO.builder().rate(courseRate.getRate()).numberOfRatedStudents(courseRate.getNumberOfRatedStudents()).build();
        }catch (Exception e){
            System.out.print(e.getMessage());
            return new DisplayRateDTO();
        }

    }
    public List<Map<String, String>> showCourseCategories() {
      List<CourseCategories> courseCategories= courseCategoriesRepo.findAll();
        Map<String,String> showCourseCategories = new HashMap<>();
        List<Map<String,String>>result=new ArrayList<>();
        for (CourseCategories courseCategories1: courseCategories) {
            showCourseCategories.put("category id",courseCategories1.getCategoryId()+"");
            showCourseCategories.put("category",courseCategories1.getCategoryTitle()+"");
            result.add(showCourseCategories);
//             showCourseCategories.add(ShowCourseCategories.builder().
//                    categoryTitle(courseCategories1.getCategoryTitle()).categoryId(courseCategories1.getCategoryId()).build());
        }

        return result;
    }
    public ShowCourseDTO showCourseService(long courseId) {
        if (courseId <= 0) {
            return ShowCourseDTO.builder().requestMessage("invalid course id").build();
        }
        return courseRepo.findById(courseId).map(course -> ShowCourseDTO.builder()
                .courseId(course.getCourseId())
                .title(course.getTitle())
                .description(course.getDescription())
                .publishDate(course.getPublishDate())
                .instructorId(course.getInstructor().getUserId())
                .instructorImage(course.getInstructor().getUserImg()!=null?course.getInstructor().getUserImg().getImgData():null)
                .instructorFullName(course.getInstructor().getFirstName()+" "+course.getInstructor().getLastName())
                .category(course.getCourseCategories().getCategoryTitle())
                .level(course.getCourseLevel())
                .courseRate(displayCourseRateService(course.getCourseId()))
                .playListId(course.getCoursePlayList()!=null?course.getCoursePlayList().getCoursePlayListId():0).requestMessage("success")
                .build()).orElse(ShowCourseDTO.builder().requestMessage("fail").build());
    }
    public List<ShowPostedCourseDTO> showPostedCourse(long userId){
        List<ShowPostedCourseDTO> showPostedCourse=new ArrayList<>();
        if(userId<=0){
            showPostedCourse.add(ShowPostedCourseDTO.builder().requestMessage("invalid user id").build());
            return showPostedCourse;
        }
        List<Course> courses= courseRepo.findByInstructorUserId(userId);
        for (Course course:courses) {
            showPostedCourse.add(ShowPostedCourseDTO.builder().courseId(course.getCourseId())
                    .title(course.getTitle())
                    .category(course.getCourseCategories().getCategoryTitle())
                    .contentImg(null)
                    .rate(displayCourseRateService(course.getCourseId()).getRate())
                    .publishDate(course.getPublishDate())
                    .requestMessage("success").build());
        }


        return showPostedCourse;
    }
    public byte[] playCourseService(long courseId) {
        return courseId>=0&&courseRepo.existsById(courseId)?courseRepo.getByCourseId(courseId):null;
    }
    public String uploadVideoService(long courseId, MultipartFile video) throws IOException {
        if (courseId>0&&video!=null){
            courseRepo.updateCourseVideo(courseId,Video.builder().videoData(video.getBytes()).content(video.getContentType()).size(video.getSize()).build());
        }
        return courseRepo.getVideoVideoIdById(courseId)>0?"success":"fail";
    }
    public Collection<?> searchForCourseService(String searchContent,String categoryTitle){
        if(searchContent.isEmpty()){
            return null;
        }
       return null;
    }
}
