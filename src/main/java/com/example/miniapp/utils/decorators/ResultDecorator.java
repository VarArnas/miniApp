package com.example.miniapp.utils.decorators;

import com.example.miniapp.interfaces.ITimerCalculation;

public class ResultDecorator extends TimerCalculationDecorator{
    public ResultDecorator(ITimerCalculation decoratedCalculation) {
        super(decoratedCalculation);
    }

    @Override
    public int calculateResult() {
        int result = super.calculateResult();
        System.out.println("The result of the calculation was: " + result);
        return result;
    }
}
