package com.spu.OEP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;
    private String categoryTitle;
    @Column
    @OneToMany(mappedBy = "courseId",fetch = FetchType.EAGER)
    private Collection<Course> courses;
}
