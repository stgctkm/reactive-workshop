package spring.workshop.stockquotes.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TradingCompanyRepository extends ReactiveMongoRepository<TradingCompany, String> {
    Mono<TradingCompany> findByTicker(String ticker);
}
