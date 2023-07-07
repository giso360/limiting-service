package com.example.limitingservice;

import lombok.Getter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class LimitingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitingServiceApplication.class, args);
	}

}
