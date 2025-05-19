package com.example.miniapp.utils.decorators;

import com.example.miniapp.interfaces.ITimerCalculation;
import org.springframework.stereotype.Component;

@Component
public class StatusDecorator extends TimerCalculationDecorator{
    public StatusDecorator(ITimerCalculation decoratedCalculation) {
        super(decoratedCalculation);
    }

    @Override
    public int calculateResult() {
        System.out.println("Status: started calculation");
        int result = decoratedCalculation.calculateResult();
        System.out.println("Status: completed calculation");
        return result;
    }
}
