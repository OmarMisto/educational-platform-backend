package com.spu.OEP.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeleteAccountDTO {
    private long accountId;
    private String email;
    private String password;
}
