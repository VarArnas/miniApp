package com.example.miniapp.utils.decorators;

import com.example.miniapp.interfaces.ITimerCalculation;
import org.springframework.stereotype.Component;

@Component
public abstract class TimerCalculationDecorator implements ITimerCalculation {
    protected final ITimerCalculation decoratedCalculation;

    protected TimerCalculationDecorator(ITimerCalculation decoratedCalculation) {
        this.decoratedCalculation = decoratedCalculation;
    }

    @Override
    public abstract int calculateResult();
}
