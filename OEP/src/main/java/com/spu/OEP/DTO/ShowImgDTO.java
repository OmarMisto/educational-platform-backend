package com.spu.OEP.DTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShowImgDTO {
    private long imgId;
    private MultipartFile img;
}
