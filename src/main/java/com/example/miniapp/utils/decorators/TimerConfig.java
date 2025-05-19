package com.example.miniapp.utils.decorators;

import com.example.miniapp.interfaces.ITimerCalculation;
import com.example.miniapp.utils.alternatives.DefaultTimerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimerConfig {
    @Bean
    @ConditionalOnProperty(name = "timer.mode", havingValue = "default")
    public static ITimerCalculation timerCalculation() {
        return new ResultDecorator(
                new StatusDecorator(
                        new DefaultTimerService()
                )
        );
    }
}
