package com.spu.OEP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Collection;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column
    private CourseLevels courseLevel;
//    @OneToOne
//    CourseRate courseRate;
//    @DateTimeFormat(pattern ="M/d/yy")
    private final LocalDateTime publishDate=LocalDateTime.now();
    @OneToOne(cascade = CascadeType.ALL)
    private Video video;
    @ManyToOne
    private Instructor instructor;
    @ManyToOne
    private CourseCategories courseCategories;
    @OneToMany(mappedBy = "reqId",cascade = CascadeType.ALL)
    private Collection<CourseRequirements> courseRequirements;
    @OneToMany(mappedBy = "toolId",cascade = CascadeType.ALL)
    private Collection<CourseTools> courseTools;
    @ManyToOne
    private CoursePlayList coursePlayList;
    @OneToMany(mappedBy = "commentId",cascade = CascadeType.ALL)
    private Collection<Comment>comments;
    @OneToMany(mappedBy = "chapterId",cascade = CascadeType.ALL)
    private Collection<CourseChapter> courseChapters;

}
