package service.accounts.service.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import service.accounts.service.strategy.CreatedAccountStrategy;

import java.util.Map;
// estrategia de creacion
@Component
public class CreatedAccountStrategyFactory {
    private final Map<String, CreatedAccountStrategy> strategies;

    @Autowired
    public CreatedAccountStrategyFactory(Map<String, CreatedAccountStrategy> strategies) {
        this.strategies = strategies;
    }

    public Mono<CreatedAccountStrategy> getStrategy(String accountType) {
        return Mono.just(strategies.get(accountType.toUpperCase()));
    }
}
