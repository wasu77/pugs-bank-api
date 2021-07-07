package pl.mwezyk.pugsbankapi.currency

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.lang.RuntimeException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class CurrencyServiceTest {

    private val DOLLAR_EXCHANGE_RATE = "4.12"

    @Test
    fun givenCurrencyCode_whenCallToCurrencyClient_thenReturnExchangeRate() {
        val currencyClientMock: NbpCurrencyClient = mock()
        whenever(currencyClientMock.getExchangeRate(eq(CurrencyCode.USD))).thenReturn(provideCurrencyRate())

        val currencyService = NbpCurrencyService(currencyClientMock)

        val exchangeRate = currencyService.getExchangeRate()

        assertNotNull(exchangeRate)
        assertEquals(DOLLAR_EXCHANGE_RATE, exchangeRate.toString())
    }

    @Test
    fun givenCurrencyCode_whenCallToNotWorkingCurrencyClient_thenAssertThrows() {
        val currencyClientMock: NbpCurrencyClient = mock()
        whenever(currencyClientMock.getExchangeRate(eq(CurrencyCode.USD))).thenThrow(RuntimeException())

        val currencyService = NbpCurrencyService(currencyClientMock)

        assertFailsWith<RuntimeException> {
            currencyService.getExchangeRate()
        }

    }

    private fun provideCurrencyRate(): NbpCurrencyExchangeResponse {
        val response = NbpCurrencyExchangeResponse()
        val rate = NbpCurrencyExchangeResponse.CurrencyRate()
        rate.setExchangeRate(DOLLAR_EXCHANGE_RATE)

        val currencyRates = NbpCurrencyExchangeResponse.CurrencyRates()
        currencyRates.setCurrencyRate(rate)

        response.setRates(listOf(currencyRates))
        return response
    }

}