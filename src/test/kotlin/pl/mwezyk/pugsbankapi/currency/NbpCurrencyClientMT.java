package pl.mwezyk.pugsbankapi.currency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

@Slf4j
class NbpCurrencyClientMT {

    NbpCurrencyClient currencyClient;

    @BeforeEach
    void setUp() {
        currencyClient = new NbpCurrencyClient(new RestTemplateBuilder().build());
    }

    @Test
    void getExchangeRate() {
        NbpCurrencyExchangeResponse response = currencyClient.getExchangeRate(CurrencyCode.USD);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(CurrencyCode.USD.name(), response.getCurrencyCode());
        Assertions.assertEquals(1, response.getRates().size());
        String exchangeRate = response.getRates().get(0).getCurrencyRate().getExchangeRate();
        Assertions.assertNotNull(exchangeRate);
        log.info("Actual USD exchange rate: {}", exchangeRate);
    }
}