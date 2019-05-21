package spring.workshop.tradingservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class QuotesClient {

    WebClient webClient;

    public Flux<Quote> quotesFeed() {
        return webClient.get()
                .uri("http://localhost:8081/quotes")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Quote.class)
                .log()
                ;

    }

    public Mono<Quote> getLatestQuote(String ticker) {
        return quotesFeed().filter(it -> it.getTicker().equals(ticker))
                .next()
                .timeout(Duration.ofSeconds(15), Mono.just(new Quote(ticker)));

    }

    public QuotesClient(WebClient.Builder builder) {
        this.webClient= builder.build();
    }
}
