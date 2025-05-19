package com.example.miniapp.services;

import com.example.miniapp.interfaces.ITimerCalculation;
import com.example.miniapp.utils.decorators.TimerConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TimerService {

    private CompletableFuture<Integer> counter = new CompletableFuture<>();
    private boolean isRunning = false;
    private final ITimerCalculation timerCalculation;

    public void startTimer() {
        isRunning = true;
        counter = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("started");
                Thread.sleep(10000);
                System.out.println("stopped");
                return timerCalculation.calculateResult();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally{
                isRunning = false;
            }
        });
    }

    public boolean isDone() {
        return counter != null && counter.isDone();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getResult() {
        if(counter.isDone()) {
            return counter.join();
        } else{
            return 0;
        }
    }
}
