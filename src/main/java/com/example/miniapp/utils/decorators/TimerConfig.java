package com.example.miniapp.utils.decorators;

import com.example.miniapp.interfaces.ITimerCalculation;
import com.example.miniapp.utils.alternatives.DefaultTimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TimerConfig {

    @Bean
    @Primary
    @ConditionalOnProperty(name = "decorator.enabled", havingValue = "true")
    public ITimerCalculation decoratedTimer(ITimerCalculation baseImplementation) {
        System.out.println("decoratedTimer");
        return new ResultDecorator(
                new StatusDecorator(
                        baseImplementation
                )
        );
    }

    @Bean
    @Primary
    @ConditionalOnProperty(name = "decorator.enabled", havingValue = "false", matchIfMissing = true)
    public ITimerCalculation plainTimer(ITimerCalculation baseImplementation) {
        System.out.println("not decorated");
        return baseImplementation;
    }
}
