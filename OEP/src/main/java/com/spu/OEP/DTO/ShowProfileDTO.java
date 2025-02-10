package com.spu.OEP.DTO;

import com.spu.OEP.model.UserImg;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ShowProfileDTO {
    private String email;
    private String firstName;
    private String lastName;
    private UserImg userImg;
    private String bio;
    private float rating;
}
