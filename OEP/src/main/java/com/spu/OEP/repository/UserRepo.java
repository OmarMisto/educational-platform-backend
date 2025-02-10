package com.spu.OEP.repository;

import com.spu.OEP.model.Account;
import com.spu.OEP.model.User;
import com.spu.OEP.model.UserImg;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByAccountEmail(String email);
    void deleteByAccountAccountId(long accountId);
    @Transactional
    @Modifying
    @Query("update User u set u.userImg=:userImg where u.account.accountId=:accountId")
    void updateUserImg(@Param("userImg") UserImg userImg,@Param("accountId") long accountId);
    @Transactional
    @Modifying
    @Query("update User u set u.firstName=:firstName, u.lastName=:lastName where u.account.accountId=:accountId")
    void updateUserById(@Param("accountId") long accountId, @Param("firstName") String firstName, @Param("lastName") String lastName);
    User findByAccount(Account build);
    Optional<User>findByAccountEmail(String email);




}
