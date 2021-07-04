package pl.mwezyk.pugsbankapi.currency

import org.springframework.stereotype.Service

@Service
class NbpCurrencyService(currencyClient: NbpCurrencyClient) : CurrencyService {

    private val client = currencyClient;

    override fun getExchangeRate(currency: Currency): Double {
        val response = client.getExchangeRate(currency) ?: throw EmptyExchangeRatesException("Empty exchange rate list, not able to process")

        val exchangeRate = response.rates.first().currencyRate.exchangeRate
        return exchangeRate.toDouble()
    }

    private class EmptyExchangeRatesException(message: String): RuntimeException(message)

}