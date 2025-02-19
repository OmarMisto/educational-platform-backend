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
@Table
public class CoursePlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long coursePlayListId;
    @Column
    private String listName;
    @Column
    private final LocalDateTime lastUpdate=LocalDateTime.now();
    @Column
    @OneToMany(mappedBy = "courseId",cascade = CascadeType.ALL)
    private Collection<Course> courses;
    @OneToOne
    private Instructor instructor;
}
