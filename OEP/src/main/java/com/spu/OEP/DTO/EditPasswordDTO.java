package com.spu.OEP.DTO;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EditPasswordDTO {
    private long accountId;
    private String email;
    private String oldPassword;
    private String newPassword;
}
