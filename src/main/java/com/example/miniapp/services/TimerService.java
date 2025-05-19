package com.example.miniapp.services;

import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class TimerService {
    private CompletableFuture<Integer> counter = new CompletableFuture<>();
    private boolean isRunning = false;


    public void startTimer() {
        isRunning = true;
        counter = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("started!!!!");
                Thread.sleep(10000);
                System.out.println("stopped!!!!");
                return 55;
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
