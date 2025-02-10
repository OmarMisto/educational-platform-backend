package com.spu.OEP.service;

import com.spu.OEP.model.User;
import com.spu.OEP.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {

    UserRepo userRepo;
    @Autowired
    UserManagementService(UserRepo userRepo){
        this.userRepo=userRepo;

    }
    public User findUserByIdService(long id){
        if (id > 0) return userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User Not found"));
        return new User();
    }
}
