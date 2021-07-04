package pl.mwezyk.pugsbankapi.currency

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
@Slf4j
class NbpCurrencyClient(var restTemplate: RestTemplate) {

    private val NBP_REST_URL = "http://api.nbp.pl/api/exchangerates/rates/a/"

    fun getExchangeRate(currency: Currency): NbpCurrencyExchangeResponse? {
        val response = this.restTemplate
            .exchange(
                createUrl(currency),
                HttpMethod.GET,
                HttpEntity<Any?>(getHeaders()),
                NbpCurrencyExchangeResponse::class.java
            )

        return response.body
    }

    private fun createUrl(currency: Currency): String {
        return NBP_REST_URL + currency.name
    }

    private fun getHeaders(): HttpHeaders {
        val headers = HttpHeaders()
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML_VALUE)
        return headers
    }
}