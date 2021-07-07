package pl.mwezyk.pugsbankapi.currency

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.lang.RuntimeException
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class CurrencyServiceTest {

    private val DOLLAR_EXCHANGE_RATE = "4.00"

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

    @Test
    fun givenCurrencyCode_whenCurrencyClientReturnsEmpty_thenAssertCustomExceptionThrows() {
        val currencyClientMock: NbpCurrencyClient = mock()
        whenever(currencyClientMock.getExchangeRate(eq(CurrencyCode.USD))).thenReturn(null)

        val currencyService = NbpCurrencyService(currencyClientMock)

        assertFailsWith<NbpCurrencyService.EmptyExchangeRatesException> {
            currencyService.getExchangeRate()
        }
    }

    @ParameterizedTest
    @MethodSource("provideAccountBalanceInPolishGroszeAndExpectedAmountInDollars")
    fun givenExchangeRateAndAccountBalance_whenConvertToCurrency_assertExpectedAmount(amount: Long, expectedAmount: String) {
        val currencyClientMock: NbpCurrencyClient = mock()
        whenever(currencyClientMock.getExchangeRate(eq(CurrencyCode.USD))).thenReturn(provideCurrencyRate())

        val currencyService = NbpCurrencyService(currencyClientMock)

        val convertedAmount = currencyService.convertToCurrency(amount)
        assertEquals(expectedAmount, convertedAmount)
    }

    private companion object {
        @JvmStatic
        fun provideAccountBalanceInPolishGroszeAndExpectedAmountInDollars() = Stream.of(
            Arguments.of(10000, "$25.00"),
            Arguments.of(0, "$0.00"),
            Arguments.of(-1000, "-$2.50")
        )
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