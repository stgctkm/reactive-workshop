package spring.workshop.tradingservice.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.workshop.tradingservice.Quote;
import spring.workshop.tradingservice.QuotesClient;
import spring.workshop.tradingservice.TickerNotFoundException;
import spring.workshop.tradingservice.domain.TradingCompanySummary;
import spring.workshop.tradingservice.infrastructure.TradingCompanyClient;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
public class QuotesController {

    TradingCompanyClient tradingCompanyClient;

    QuotesClient quotesClient;


    @GetMapping(path = "/quotes/feed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    Flux<Quote> feed() {
       return quotesClient.quotesFeed();
    }

    @GetMapping(path = "/quotes/summary/{ticker}", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mono<TradingCompanySummary> quotesDetails(@PathVariable String ticker) {
        return tradingCompanyClient.getTradingCompany(ticker)
                .zipWith(this.quotesClient.getLatestQuote(ticker),
                        TradingCompanySummary::new);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TickerNotFoundException.class)
    public void onTickerNotFound() {
    }


    public QuotesController(TradingCompanyClient tradingCompanyClient, QuotesClient quotesClient) {
        this.tradingCompanyClient = tradingCompanyClient;
        this.quotesClient = quotesClient;
    }
}
