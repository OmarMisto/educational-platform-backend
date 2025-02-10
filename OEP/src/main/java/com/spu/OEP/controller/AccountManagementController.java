package com.spu.OEP.controller;

import com.spu.OEP.DTO.*;
import com.spu.OEP.service.AccountManagementService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("account/management")
public class AccountManagementController {
    @Autowired
    private AccountManagementService accountManagementService;
    @DeleteMapping("delete_account")
    public ResponseEntity<String>deleteAccount(@RequestBody DeleteAccountDTO deleteAccountDTO){
        return ResponseEntity.ok(accountManagementService.deleteAccountService(deleteAccountDTO));
    }
    @PostMapping("edit/password")
    public ResponseEntity<String>editPassword(@RequestBody EditPasswordDTO editPasswordDTO){
        return ResponseEntity.ok(accountManagementService.editPasswordService(editPasswordDTO));
    }
    @PostMapping("upload/account/img")
    public ResponseEntity<String>uploadAccountImg(@RequestParam("accountId") long accountId,@RequestPart("uploadedImg") MultipartFile uploadedImage) throws IOException {
        return ResponseEntity.ok(accountManagementService.uploadImgService(UploadImgDTO.builder().accountId(accountId).uploadedImg(uploadedImage).build()));
    }
    @GetMapping("show/account/img/")
    public ResponseEntity<byte[]> showUserProfileImage(@RequestParam("accountId")long accountId){
       byte[] imgData=accountManagementService.showUserProfileImg(accountId);
       if (imgData ==null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(imgData);
    }
    @PostMapping("edit/account/info")
    public ResponseEntity<String>editAccountInfo(@RequestBody EditAccountInfoDTO editAccountInfoDTO){
        return ResponseEntity.ok(accountManagementService.editAccountInfoService(editAccountInfoDTO));
  }
    @GetMapping("get/profile/{accountId}")
    public ResponseEntity<ShowProfileDTO>getProfile(@PathVariable("accountId")long accountId){
        return ResponseEntity.ok(accountManagementService.getProfileService(accountId));
    }
    @PostMapping("/activate/account/{accountId}")
    public ResponseEntity<String>activateAccount(@PathVariable("accountId")long accountId){
        return ResponseEntity.ok(accountManagementService.activateAccountService(accountId));
    }
    @PostMapping("/deActivate/account/{accountId}")
    public ResponseEntity<String>deActivateAccount(@PathVariable("accountId")long accountId){
        return ResponseEntity.ok(accountManagementService.deActivateAccountService(accountId));
    }
}
