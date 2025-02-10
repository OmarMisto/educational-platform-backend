package com.spu.OEP.service;

import com.spu.OEP.DTO.*;

import com.spu.OEP.model.UserImg;
import com.spu.OEP.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountManagementServiceTest {
    @Autowired
    private AccountManagementService accountManagementService;
    @Test
    void testTheSuccessOfDeleteAccount(){
        assertEquals("success", accountManagementService.deleteAccountService(DeleteAccountDTO.builder().
                accountId(1).email("omarmisto53@gmail.com").password("1234567890").build()));
    }
    @Test
    void testTheFailureOfDeleteAccountWithUnExitAccountId(){
        assertEquals("the account is not found", accountManagementService.deleteAccountService(DeleteAccountDTO.builder().
                accountId(1).email("omarmisto53@gmail.com").password("1234567890").build()));
    }
    @Test
    void testTheFailureOfDeleteAccountWithWrongEmailOrPassword(){
        assertEquals("mismatch email or password", accountManagementService.deleteAccountService(DeleteAccountDTO.builder().
                accountId(3).email("omarmisto@gmail.com").password("1234567890").build()));
    }
    @Test
    void testTheSuccessOfCheckingMissingValues(){
        assertEquals("Invalid input", accountManagementService.deleteAccountService(DeleteAccountDTO.builder().
                email("omarmisto53@gmail.com").password("1234567890").build()));
    }

    @Test
    void testSuccessOfEditPassword(){
        assertEquals("success",accountManagementService.editPasswordService(EditPasswordDTO.builder().accountId(2).email("omarmisto53@gmail.com")
                .oldPassword("1234567890").newPassword("111111111").build()));
    }
    @Test
    void testFailureOfEditPasswordWithShortPassword(){
        assertEquals("the new password: 1111 is less than 8 charts",accountManagementService.editPasswordService(EditPasswordDTO.builder().accountId(2).email("omarmisto53@gmail.com")
                .oldPassword("111111111").newPassword("1111").build()));
    }
    @Test
    void testSuccessOfUploadImgService() throws IOException {
        MultipartFile uploadedImg = new MockMultipartFile("file", "image.png", "image/png", new byte[1024]);
        UploadImgDTO uploadImgDTO = UploadImgDTO.builder()
                .uploadedImg(uploadedImg)
                .accountId(1)
                .build();
        String result = accountManagementService.uploadImgService(uploadImgDTO);
        assertEquals("success", result);
    }
    @Test
    void testTheSuccessOfEditAccountInfo(){
        assertEquals("success",
                accountManagementService.editAccountInfoService(EditAccountInfoDTO.builder().accountId(2).firstName("Ali").
                       email("omarmisto53@gmail.com").bio("working for love").build()));
    }
    @Test
    void testTheSuccessOfShowAccount(){
        System.out.println((accountManagementService.getProfileService(1)).toString());
    }
    @Test
    void testSuccessOfActivateAccount(){

        assertEquals("success",accountManagementService.activateAccountService(1));
    }
    @Test
    void testSuccessOfDeActivateAccount(){
        assertEquals("success",accountManagementService.deActivateAccountService(1));
    }
}