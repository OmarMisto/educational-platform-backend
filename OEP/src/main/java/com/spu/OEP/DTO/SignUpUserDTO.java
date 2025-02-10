package com.spu.OEP.DTO;

import com.spu.OEP.model.UserImg;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SignUpUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String bio;
    private UserImg userImg;
    private String userType;//Student,Instructor,Admin
    private String majority;
    private int experienceYears;
}
