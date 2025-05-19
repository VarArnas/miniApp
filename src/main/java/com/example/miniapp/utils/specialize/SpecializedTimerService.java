package com.example.miniapp.utils.specialize;

import com.example.miniapp.utils.alternatives.DefaultTimerService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SpecializedTimerService extends DefaultTimerService {
    @Override
    public int calculateResult() {
        System.out.println("Running specialized calculation");
        return super.calculateResult() + 10;
    }
}