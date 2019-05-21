package spring.workshop.stockquotes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.workshop.stockquotes.domain.TradingCompany;
import spring.workshop.stockquotes.domain.TradingCompanyRepository;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Component
public class TradingCompanyCommandLineRunner implements CommandLineRunner {

    TradingCompanyRepository tradingCompanyRepository;

    @Override
    public void run(String... strings) {
        List<TradingCompany> companies = Arrays.asList(
                new TradingCompany("Pivotal Software", "PVTL"),
                new TradingCompany("Dell Technologies", "DELL"),
                new TradingCompany("Google", "GOOG"),
                new TradingCompany("Microsoft", "MSFT"),
                new TradingCompany("Oracle", "ORCL"),
                new TradingCompany("Red Hat", "RHT"),
                new TradingCompany("Vmware", "VMW")
        );
        this.tradingCompanyRepository.insert(companies).blockLast(Duration.ofSeconds(30));
    }

    TradingCompanyCommandLineRunner(TradingCompanyRepository tradingCompanyRepository) {
        this.tradingCompanyRepository = tradingCompanyRepository;
    }
}
