package com.spu.OEP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rateId;
    private long numberOfRatedStudents;
    private float rate;
    @OneToOne
    private Course course;
}
