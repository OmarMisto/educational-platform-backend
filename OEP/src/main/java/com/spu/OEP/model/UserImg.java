package com.spu.OEP.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class UserImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imgId;
    private String contentType;
    private long size;
    @Lob
    private byte[] imgData;
}
