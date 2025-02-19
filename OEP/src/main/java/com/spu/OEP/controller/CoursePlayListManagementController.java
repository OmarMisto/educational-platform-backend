package com.spu.OEP.controller;

import com.spu.OEP.DTO.AddCourseToPlayListDTO;
import com.spu.OEP.DTO.CreatePlayListDTO;
import com.spu.OEP.DTO.ViewPlayLists;
import com.spu.OEP.service.CoursePlatListManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("playlist/management")
public class CoursePlayListManagementController {
    @Autowired
    private CoursePlatListManagementService coursePlatListManagementService;
    @GetMapping("view/Playlists/{instructorId}")
    public ResponseEntity<List<ViewPlayLists>>viewPlayListsController(@PathVariable("instructorId") long instructorId){
        return ResponseEntity.ok(coursePlatListManagementService.viewPlayListsService(instructorId));
    }
    @PostMapping("create/playlist")
    public ResponseEntity<String> createPlayListController(@RequestBody CreatePlayListDTO createPlayListDTO){
        return ResponseEntity.ok(coursePlatListManagementService.createPlayListService(createPlayListDTO));
    }
    @DeleteMapping("delete/playlist/{playListId}")
    public ResponseEntity<String>deletePlayListController(@PathVariable("playListId")long playListId){
        return ResponseEntity.ok(coursePlatListManagementService.deletePlayList(playListId));
    }
    @PostMapping("add/course/to/playlist")
    public ResponseEntity<String>addCourseToPlayList(@RequestBody AddCourseToPlayListDTO addCourseToPlayListDTO){
        return ResponseEntity.ok(coursePlatListManagementService.addCourseToPlayList(addCourseToPlayListDTO));
    }
    @PostMapping("remove/course/from/playlist")
    public ResponseEntity<String>removeCourseFormPlayList(@RequestParam("playListId")long playListId){
        return ResponseEntity.ok(coursePlatListManagementService.removeCourseFromPlayList(playListId));
    }
}
