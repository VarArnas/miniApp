package com.example.miniapp.utils.alternatives;

import com.example.miniapp.interfaces.ITimerCalculation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "timer.mode", havingValue = "alternative")
public class AlternativeTimerService implements ITimerCalculation {
    @Override
    public int calculateResult() {
        return 100;
    }
}
