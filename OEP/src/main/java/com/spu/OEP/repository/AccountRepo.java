package com.spu.OEP.repository;

import com.spu.OEP.model.Account;
import com.spu.OEP.model.UserImg;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
    boolean existsByEmailAndPassword(String email, String oldPassword);
    @Transactional
    @Modifying
    @Query("update Account a set a.password=:newPassword where a.accountId=:accountId ")
    void updatePassword(@Param("newPassword") String newPassword,@Param("accountId")Long accountId);
    @Modifying
    @Transactional
    @Query("update Account a set a.email=:email, a.bio=:bio where a.accountId=:accountId")
    int updateAccountById(@Param("accountId") long accountId, @Param("email") String email, @Param("bio") String bio);
//    @Transactional
//    @Modifying
//    @Query("update Account a set a.user.userImg=:userImg where a.accountId=:accountId")
//    void updateAccountUserUserImg(@Param("userImg") UserImg userImg,@Param("accountId") long accountId);//to delete

    @Transactional
    @Modifying
    @Query("update Account a set a.status=:status where a.accountId=:accountId")
    void updateByStatus(@Param("accountId") long accountId,@Param("status") boolean status);
    @Query(value = "select img_data from user_img where img_id= (select img_id from user where account_account_id=:accountId)",nativeQuery = true)
    byte[] getUserImgByAccountId(@Param("accountId") long accountId);

}
