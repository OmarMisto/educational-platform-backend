package com.spu.OEP.repository;

import com.spu.OEP.model.RegistrationSecretKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationSecretKeyRepo extends JpaRepository<RegistrationSecretKey,Long> {


    boolean existsBySecretKey(String code);
}
