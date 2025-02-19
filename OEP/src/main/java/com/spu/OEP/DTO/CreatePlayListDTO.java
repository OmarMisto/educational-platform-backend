package com.spu.OEP.DTO;

import com.spu.OEP.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreatePlayListDTO {
    private String playListName;
    private long instructorUserId;
}
