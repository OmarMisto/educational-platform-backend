package com.spu.OEP.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Instructor extends User{
    private String majority;
    private int experienceYears;
    private float rate;
    @OneToMany(mappedBy = "courseId")
    private Collection<Course>courses;
}
