package com.spu.OEP.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Builder
@Getter
@Setter
@ToString
public class ViewPlayLists {
    private long playlistId;
    private long instructorId;
    private String listName;
    private byte[] contentImg;
    private LocalDateTime lastUpdate;
    private long numOfCourses;
    private String requestMessage;
    private Object[]courseIds;

}
