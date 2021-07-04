package pl.mwezyk.pugsbankapi.currency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
//@ExtendWith(SpringExtension.class)
class NbpCurrencyClientMT {

    NbpCurrencyClient currencyClient;

    @BeforeEach
    void setUp() {
        currencyClient = new NbpCurrencyClient(new RestTemplateBuilder().build());
    }

    @Test
    void getExchangeRate() {
        NbpCurrencyExchangeResponse response = currencyClient.getExchangeRate(Currency.USD);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Currency.USD.name(), response.getCurrencyCode());
        Assertions.assertEquals(1, response.getRates().size());
        String exchangeRate = response.getRates().get(0).getCurrencyRate().getExchangeRate();
        Assertions.assertNotNull(exchangeRate);
        log.info("Actual USD exchange rate: {}", exchangeRate);
    }
}