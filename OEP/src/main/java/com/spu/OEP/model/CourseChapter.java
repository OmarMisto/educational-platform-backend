package com.spu.OEP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseChapter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long chapterId;
    private String title;
    private String description;
    private ArrayList<String> goals;
    private Time duration;
    @ManyToOne
    private Course course;
}
