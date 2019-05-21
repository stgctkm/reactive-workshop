package spring.workshop.tradingservice.presentation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.workshop.tradingservice.domain.TradingCompany;
import spring.workshop.tradingservice.infrastructure.TradingCompanyClient;

@RestController
public class TradingCompanyController {

    private final TradingCompanyClient tradingCompanyClient;

    public TradingCompanyController(TradingCompanyClient tradingCompanyClient) {
        this.tradingCompanyClient = tradingCompanyClient;
    }

    @GetMapping(path = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TradingCompany> listTradingCompanies() {
        return tradingCompanyClient.findAllCompanies();
    }

    @GetMapping(path = "/details/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TradingCompany> showTradingCompanies(@PathVariable String ticker) {
        return tradingCompanyClient.getTradingCompany(ticker);
    }

}