package com.example.limitingservice.controller;

import com.example.limitingservice.model.LimitingResponse;
import com.example.limitingservice.service.LimitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class LimitingController {

    /**
     * For Artillery usage tutorial check here:
     * https://blog.appsignal.com/2021/11/10/a-guide-to-load-testing-nodejs-apis-with-artillery.html
     * count == number of users, num == requests per user
     * issue command in cmd under C:\Users\<userName> -> artillery quick --count 1 --num 10 http://localhost:8080/limit
     */

    @Autowired
    private LimitingService limitingService;

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @GetMapping("/limit")
    public ResponseEntity<LimitingResponse> withLimiter() throws InterruptedException {
        limitingService.limit();
        LimitingResponse limitingResponse = new LimitingResponse();
        limitingResponse.setTimesInvoked(atomicInteger.incrementAndGet());
        LocalDateTime localDateTime = LocalDateTime.now();
        limitingResponse.setTime(localDateTime.truncatedTo(ChronoUnit.MILLIS));
        System.out.println("====================");
        System.out.println("LIMITING ENDPOINT INVOKED !!! - Call No: " + limitingResponse.getTimesInvoked());
        System.out.println(limitingResponse);
        System.out.println("====================");
        return ResponseEntity.ok(limitingResponse);
    }

    @GetMapping("/no-limit")
    public ResponseEntity<LimitingResponse> withoutLimiter() throws InterruptedException {

        LimitingResponse unLimitingResponse = new LimitingResponse();
        unLimitingResponse.setTimesInvoked(atomicInteger.incrementAndGet());
        LocalDateTime localDateTime = LocalDateTime.now();
        unLimitingResponse.setTime(localDateTime.truncatedTo(ChronoUnit.MILLIS));
        System.out.println("====================");
        System.out.println("LIMITELESS ENDPOINT INVOKED !!! - Call No: " + unLimitingResponse.getTimesInvoked());
        System.out.println(unLimitingResponse);
        System.out.println("====================");
        return ResponseEntity.ok(unLimitingResponse);
    }
}
