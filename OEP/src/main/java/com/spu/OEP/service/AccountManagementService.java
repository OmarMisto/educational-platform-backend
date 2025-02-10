package com.spu.OEP.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.spu.OEP.DTO.*;
import com.spu.OEP.model.Account;
import com.spu.OEP.model.User;
import com.spu.OEP.model.UserImg;
import com.spu.OEP.repository.AccountRepo;
import com.spu.OEP.repository.UserImgRepo;
import com.spu.OEP.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AccountManagementService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private UserImgRepo userImgRepo;
    @Autowired
    private UserRepo userRepo;
    private static String passwordEncryptionMD5Tch(String password){
        String encryptedpassword = null;
        try
        {

            MessageDigest m = MessageDigest.getInstance("MD5");


            m.update(password.getBytes());


            byte[] bytes = m.digest();


            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }


            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        System.out.println("Plain-text password: " + password);
        System.out.println("Encrypted password using MD5: " + encryptedpassword);
        return encryptedpassword;
    }
    @Transactional
    public String deleteAccountService(DeleteAccountDTO deleteAccountDTO){
        if (deleteAccountDTO == null ||
                deleteAccountDTO.getAccountId() <=0 ||
                deleteAccountDTO.getEmail() == null ||
                deleteAccountDTO.getPassword() == null) {
            return "Invalid input";
        }
        if(!accountRepo.existsById(deleteAccountDTO.getAccountId())){
            return "the account is not found";
        }
        Account account;
        try{
             account= accountRepo.findById(deleteAccountDTO.getAccountId()).
                     orElseThrow(()->new EntityNotFoundException("Account not found"));
        }catch (Exception e){
            return "Error will creating account"+e.getMessage();
        }
        if(account.getEmail().equals(deleteAccountDTO.getEmail())&&account.getPassword().equals(passwordEncryptionMD5Tch(deleteAccountDTO.getPassword()))){
            userRepo.deleteByAccountAccountId(deleteAccountDTO.getAccountId());
            return "success";
        }
        return "mismatch email or password";
    }
    @Transactional
    public String editPasswordService(EditPasswordDTO editPasswordDTO){
        if(editPasswordDTO==null||
                editPasswordDTO.getEmail()==null||
                editPasswordDTO.getOldPassword()==null||
                editPasswordDTO.getNewPassword()==null||
                editPasswordDTO.getAccountId()<=0){
            return "Invalid input";
        }
        if(accountRepo.existsByEmailAndPassword(editPasswordDTO.getEmail(),passwordEncryptionMD5Tch(editPasswordDTO.getOldPassword()))){
            if(editPasswordDTO.getNewPassword().length()>8){
                try {
                    accountRepo.updatePassword(passwordEncryptionMD5Tch(editPasswordDTO.getNewPassword()), editPasswordDTO.getAccountId());
                    return "success";
                }catch (Exception e){
                    return "Error while updating password "+e.getMessage();
                }
            }
            return "the new password: "+editPasswordDTO.getNewPassword()+" is less than 8 charts";
        }
        return "mismatch email or password";
    }
    @Transactional
    public String uploadImgService(UploadImgDTO uploadImgDTO) throws IOException {
        if(uploadImgDTO==null||uploadImgDTO.getUploadedImg().isEmpty()||uploadImgDTO.getUploadedImg().getContentType()==null||uploadImgDTO.getAccountId()<=0){
            return "Invalid Input";
        }
        if (uploadImgDTO.getUploadedImg().getSize()> 5 * 1024 * 1024) {
            return "Image size exceeds the maximum limit of 5MB.";
        }
        UserImg userImg=UserImg.builder().
                contentType(uploadImgDTO.getUploadedImg().getContentType()).size(uploadImgDTO.getUploadedImg().getSize()).
                imgData(uploadImgDTO.getUploadedImg().getBytes()).build();
        try {
            userImgRepo.save(userImg);
            userRepo.updateUserImg(userImg,uploadImgDTO.getAccountId());
            return "success";
        }catch (Exception e){
            return "Error while uploading image"+e.getMessage();
        }
        // TODO: ١٧/١١/٢٠٢٤  isValidType();
    }//not working
    @Transactional
    public String editAccountInfoService(EditAccountInfoDTO editAccountInfoDTO) {
        if(editAccountInfoDTO.getAccountId()<=0){
            return "Invalid Input";
        }
        if(!accountRepo.existsById(editAccountInfoDTO.getAccountId())){
            return "The account is not found";
        }
        try {
            User user=userRepo.findByAccount(Account.builder().accountId(editAccountInfoDTO.getAccountId()).build());
            if(editAccountInfoDTO.getEmail()!=null) user.getAccount().setEmail(editAccountInfoDTO.getEmail());
            if(editAccountInfoDTO.getBio()!=null) user.getAccount().setBio(editAccountInfoDTO.getBio());
            if(editAccountInfoDTO.getFirstName()!=null) user.setFirstName(editAccountInfoDTO.getFirstName());
            if(editAccountInfoDTO.getLastName()!=null) user.setLastName(editAccountInfoDTO.getLastName());

            accountRepo.updateAccountById(user.getAccount().getAccountId(),user.getAccount().getEmail(),user.getAccount().getBio());
            userRepo.updateUserById(user.getAccount().getAccountId(),user.getFirstName(),user.getLastName());
            return "success";
        }catch (Exception e){
            return "Error while updating user info"+e.getMessage();
        }
    }
    public  ShowProfileDTO getProfileService(long accountId) {
        if(accountId<= 0){
            return new ShowProfileDTO();
        }
        if (!accountRepo.existsById(accountId)){
            return new ShowProfileDTO();
        }
        try {
           User user= userRepo.findByAccount(Account.builder().accountId(accountId).build());
            return ShowProfileDTO.builder().email(user.getAccount().getEmail()).
                    firstName(user.getFirstName()).lastName(user.getLastName()).
                    bio(user.getAccount().getBio()).userImg(user.getUserImg()).build();
        }catch (Exception e){
            return new ShowProfileDTO();
        }
    }//rating issue
    public String activateAccountService(long accountId) {
        if(accountId<=0||!accountRepo.existsById(accountId)){
            return "Invalid account";
        }
        accountRepo.updateByStatus(accountId,true);
        return "success";
    }
    public String deActivateAccountService(long accountId) {
        if(accountId<=0||!accountRepo.existsById(accountId)){
            return "Invalid account";
        }
        accountRepo.updateByStatus(accountId,false);
        return "success";
    }
    public User loadUserByEmail(String email){
        return userRepo.findByAccountEmail(email).orElseThrow(()->new EntityNotFoundException("User Not found"));
    }

    public byte[] showUserProfileImg(@NonNull long accountId) {
        if (accountId<=0 || !accountRepo.existsById(accountId)){
             return null;
        }
        return accountRepo.getUserImgByAccountId(accountId);

    }

}
