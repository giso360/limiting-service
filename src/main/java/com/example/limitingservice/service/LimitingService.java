package com.example.limitingservice.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class LimitingService {

    private final Timer timer = new Timer(true);

    private final TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            signals.offer("A");
        }
    };

    private final AtomicBoolean isEndpointCalledForFirstTime = new AtomicBoolean(false);

    private static final long MAX_CALLS_PER_SEC = 1L; // this is frequency ... inverse of period

    @Getter
    private ArrayBlockingQueue<String> signals = new ArrayBlockingQueue<>((int) MAX_CALLS_PER_SEC);

    public void limit() throws InterruptedException {
        if (!isEndpointCalledForFirstTime.getAndSet(true)) {
            timer.scheduleAtFixedRate(this.timerTask, 0L, Math.floorDiv(1000L, MAX_CALLS_PER_SEC));
        }
        signals.take();
    }
}
