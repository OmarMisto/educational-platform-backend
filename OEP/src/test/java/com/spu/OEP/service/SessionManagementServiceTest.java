package com.spu.OEP.service;

import com.spu.OEP.DTO.SignUpUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SessionManagementServiceTest {
    @Autowired
    SessionManagementService sessionManagementService;
    @Test
    void testSuccessOfSignUpServiceWithValidData (){
        assertEquals ("success",sessionManagementService.signUpService(SignUpUserDTO.builder().firstName("Ahmed").
                lastName("Saleh").email("ahmedsaleh55@gmail.com").password("1234567890").userType("instructor").bio("Work for future").build()));
    }
    @Test
    void testTheFailureOfSignUpWithAlreadyInUseEmail() throws NoSuchAlgorithmException {
        String email="omarmisto53@gmail.com";
        assertEquals("the"+email+" is already exist try with another email",sessionManagementService.validateUserEmailService(email));
    }

    @Test
    void testFailOfSignUpWithMissingData(){
        assertEquals ("missing failed error",sessionManagementService.signUpService(SignUpUserDTO.builder().
                lastName("Mesto").email("omarmisto53@gmail.com").password("1234567890").userType("student").bio("Work for future").build()));
    }
    @Test
    void testSuccessOfPasswordLenCheck(){
        assertEquals ("Success",sessionManagementService.signUpService(SignUpUserDTO.builder().firstName("Omar").
                lastName("Mesto").email("omarmisto53@gmail.com").password("12341234").userType("student").bio("Work for future").build()));
    }
    @Test
    void testTheFailureOfSignUpWithInvalidPasswordLen(){
        assertEquals ("the password is too short, it should me more than 8 charters",sessionManagementService.signUpService(SignUpUserDTO.builder().firstName("Omar").
                lastName("Mesto").email("omarmisto53@gmail.com").password("1234").userType("student").bio("Work for future").build()));

    }
    @Test
    void testTheFailureOfLogInWithInvalidEmail(){
        assertEquals("fail",sessionManagementService.logInService("omar@gmail.com","123456789"));
    }
    @Test
    void testTheFailureOfLogInWithInvalidPassword(){
        assertEquals("fail",sessionManagementService.logInService("omarmisto53@gmail.com","123344"));
    }
    @Test
    void testTheSuccessOfLogInWithValidData(){
        assertNotEquals("fail",sessionManagementService.logInService("omarmisto53@gmail.com","1234567890"));
    }
    @Test
    void testTheFailureOfLogInWithBlankFields(){
        assertEquals("fail",sessionManagementService.logInService("",""));
    }

}