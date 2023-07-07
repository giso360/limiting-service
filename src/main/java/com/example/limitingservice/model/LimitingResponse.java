package com.example.limitingservice.model;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class LimitingResponse {

    private int timesInvoked;
    private LocalDateTime time;

}
