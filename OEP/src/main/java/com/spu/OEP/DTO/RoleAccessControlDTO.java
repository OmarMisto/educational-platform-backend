package com.spu.OEP.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleAccessControlDTO {
    private long userId;
    private String email;
    private String role;
    private String requestMessage;
}
