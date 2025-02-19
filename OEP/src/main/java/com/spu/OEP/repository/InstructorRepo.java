package com.spu.OEP.repository;

import com.spu.OEP.model.Instructor;

public interface InstructorRepo extends UserRepo{
    Instructor findByUserId(long instructorUserId);
}
