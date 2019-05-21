package spring.workshop.stockquotes.presentation;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import spring.workshop.stockquotes.domain.Quote;
import spring.workshop.stockquotes.domain.QuoteGenerator;

@Component
public class QuoteHandler {

    QuoteGenerator quoteGenerator;

    Mono<ServerResponse> streamQuotes(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(quoteGenerator.fetchQuoteStream(), Quote.class);
    }

    QuoteHandler(QuoteGenerator quoteGenerator) {
        this.quoteGenerator = quoteGenerator;
    }
}
