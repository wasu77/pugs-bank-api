package pl.mwezyk.pugsbankapi.currency

import lombok.extern.slf4j.Slf4j
import org.springframework.context.annotation.PropertySource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
@Slf4j
@PropertySource("classpath:application.properties")
class NbpCurrencyClient(var restTemplate: RestTemplate) {

    private val NBP_REST_URL = "http://api.nbp.pl/api/exchangerates/rates/a/"

    fun getExchangeRate(currencyCode: CurrencyCode): NbpCurrencyExchangeResponse? {
        val response = this.restTemplate
            .exchange(
                createUrl(currencyCode),
                HttpMethod.GET,
                HttpEntity<Any?>(getHeaders()),
                NbpCurrencyExchangeResponse::class.java
            )

        return response.body
    }

    private fun createUrl(currencyCode: CurrencyCode): String {
        return NBP_REST_URL + currencyCode.name
    }

    private fun getHeaders(): HttpHeaders {
        val headers = HttpHeaders()
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML_VALUE)
        return headers
    }
}