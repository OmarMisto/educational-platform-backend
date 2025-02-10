package com.spu.OEP.DTO;

import lombok.*;
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;
import org.springframework.web.multipart.MultipartFile;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UploadImgDTO {
    private long accountId;
    private MultipartFile uploadedImg;
}
