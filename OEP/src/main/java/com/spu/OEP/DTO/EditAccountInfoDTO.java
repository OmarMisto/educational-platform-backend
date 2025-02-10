package com.spu.OEP.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EditAccountInfoDTO {
    private long accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
}
