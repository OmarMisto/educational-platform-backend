package com.spu.OEP.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class ShowCourseCategories {
    private long categoryId;
    private String categoryTitle;
}
