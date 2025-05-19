package com.example.miniapp.utils.decorators;

import com.example.miniapp.interfaces.ITimerCalculation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public abstract class TimerCalculationDecorator implements ITimerCalculation {
    protected final ITimerCalculation decoratedCalculation;

    protected TimerCalculationDecorator(ITimerCalculation decoratedCalculation) {
        this.decoratedCalculation = decoratedCalculation;
    }

    @Override
    public int calculateResult(){
        return decoratedCalculation.calculateResult();
    };
}
