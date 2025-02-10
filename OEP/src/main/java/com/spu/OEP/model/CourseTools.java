package com.spu.OEP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseTools {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long toolId;
    private List<String> tools;
    @ManyToOne()
    private Course course;
}
