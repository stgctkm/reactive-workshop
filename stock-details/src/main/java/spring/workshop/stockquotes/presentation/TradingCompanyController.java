package spring.workshop.stockquotes.presentation;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.workshop.stockquotes.domain.TradingCompany;
import spring.workshop.stockquotes.domain.TradingCompanyRepository;

import java.time.Duration;

@Controller
class TradingCompanyController {

    TradingCompanyRepository tradingCompanyRepository;

    @ResponseBody
    @GetMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    Flux<TradingCompany> listTradingCompanies() {
        return tradingCompanyRepository.findAll();
    }

    @ResponseBody
    @GetMapping(value = "/details/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<TradingCompany> showTradingCompanies(@PathVariable String ticker) {
        return tradingCompanyRepository.findByTicker(ticker).delayElement(Duration.ofMillis(400));
    }

    TradingCompanyController(TradingCompanyRepository tradingCompanyRepository) {
        this.tradingCompanyRepository = tradingCompanyRepository;
    }
}
