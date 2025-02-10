package com.spu.OEP.service;

import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.spu.OEP.DTO.SignUpUserDTO;
import com.spu.OEP.SecurityConfig.JwtService;
import com.spu.OEP.model.*;
import com.spu.OEP.repository.RegistrationSecretKeyRepo;
import com.spu.OEP.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
@Service
public class SessionManagementService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RegistrationSecretKeyRepo registrationSecretKeyRepo;
    @Autowired
    private JavaMailSender javaMailSender;
    private static String passwordEncryptionMD5Tch(String password){
        String encryptedpassword = null;
        try
        {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        /* Display the unencrypted and encrypted passwords. */
        System.out.println("Plain-text password: " + password);
        System.out.println("Encrypted password using MD5: " + encryptedpassword);
        return encryptedpassword;
    }
    public String signUpService(SignUpUserDTO userDTO){
        if(userDTO.getFirstName()==null||userDTO.getLastName()==null||userDTO.getEmail()==null||userDTO.getPassword()==null){
            return "missing failed error";
        }
        if (userDTO.getPassword().length()<8){
            return "the password is too short, it should me more than 8 charters";
        }
        userDTO.setPassword(passwordEncryptionMD5Tch(userDTO.getPassword()));
        try {

            User user=userFactory(userDTO);
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setAccount(Account.builder().email(userDTO.getEmail()).password(userDTO.getPassword()).bio(userDTO.getBio()).status(true).build());
            user.setUserImg(userDTO.getUserImg());
            userRepo.save(user);
            return "success";
        }catch (Exception e){
            return "Error while saving the user"+ e.getMessage();
        }

    }
    private User userFactory(SignUpUserDTO userDTO) {
        if(userDTO.getUserType().equalsIgnoreCase("student")){
            Student student=new Student();
            student.setRole(Role.Student);
            return student;
        }
        if (userDTO.getUserType().equalsIgnoreCase("instructor")){
            Instructor instructor=new Instructor();
            instructor.setExperienceYears(userDTO.getExperienceYears());
            instructor.setMajority(userDTO.getMajority());
            instructor.setRole(Role.Instructor);
            return instructor;
        }
        if (userDTO.getUserType().equalsIgnoreCase("admin")){
            Admin admin=new Admin();
            admin.setRole(Role.Admin);
            return new  Admin();
        }
        throw new  IllegalArgumentException("Invalid user type");
    }
    public boolean validateCodeService(String code){
        if (!code.isEmpty()) return registrationSecretKeyRepo.existsBySecretKey(code);
        return false;
    }
    public String sendCodeViaEmail(String to,String secretKey){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("omarmisto53@gmail.com");
        mailMessage.setTo(to);
        mailMessage.setSubject("Course Camp Registration");
        String invitationMessage="Hi! "+to+" we are happy that you have requisted to join our community via"+to+
                " you can use this code to complete signup process: "+secretKey+" ";

        mailMessage.setText(invitationMessage);
        javaMailSender.send(mailMessage);
        return "success";
    }
    public String validateUserEmailService(String email) throws NoSuchAlgorithmException {
        if (email.isEmpty()) return "missing email failed";
        if(userRepo.existsByAccountEmail(email)){ return "the"+email+" is already exist try with another email";}
        //todo add check for email format
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey originalKey = keyGenerator.generateKey();
            RegistrationSecretKey registrationSecretKey= RegistrationSecretKey.builder().
                    secretKey(String.valueOf(originalKey).substring(31)).generationDate(LocalDateTime.now()).ownerEmail(email).build();
            registrationSecretKeyRepo.save(registrationSecretKey);
          return sendCodeViaEmail(email,registrationSecretKey.getSecretKey());

    }
    public String logInService(@NonNull String email,@NonNull String password) {
        if(userRepo.existsByAccountEmail(email)){
            User user=userRepo.findByAccountEmail(email).orElseThrow(()->new EntityNotFoundException("User not found"));
            if(user.getAccount().getPassword().equals(passwordEncryptionMD5Tch( password))&&user.getAccount().getEmail().equals(email)){
                return jwtService.generateToken(email);
            }
        }
        return "fail";
    }

}
