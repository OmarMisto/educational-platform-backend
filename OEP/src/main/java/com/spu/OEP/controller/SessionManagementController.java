package com.spu.OEP.controller;

import com.spu.OEP.DTO.LogInDTO;
import com.spu.OEP.DTO.RoleAccessControlDTO;
import com.spu.OEP.DTO.SignUpUserDTO;
import com.spu.OEP.service.SessionManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Controller
@RestController
@RequestMapping("session/management")
public class SessionManagementController {
    @Autowired
    private SessionManagementService service;
    @PutMapping("/signup")
    public ResponseEntity<String>signUpController(@RequestBody SignUpUserDTO user){
        return ResponseEntity.ok(service.signUpService(user));
    }
    @PutMapping("/validate/{email}")
    public ResponseEntity<String>validateUserEmail(@PathVariable("email")String email) throws NoSuchAlgorithmException {
        return ResponseEntity.ok(service.validateUserEmailService(email));
    }
    @PostMapping("/validate/{code}")
    public ResponseEntity<String>validateCode(@PathVariable("code")String code){
        return ResponseEntity.ok(service.validateCodeService(code)?"success":"code mismatch");
    }
    @PostMapping("/login")
    public ResponseEntity<?>logIn(@RequestBody LogInDTO logInDTO){
        return ResponseEntity.ok(service.logInService(logInDTO.getEmail(),logInDTO.getPassword()));
    }
    @GetMapping("/role/access")
    public ResponseEntity<RoleAccessControlDTO>roleAccessControl(String email){
        return ResponseEntity.ok(service.roleAccessService(email));
    }

}
