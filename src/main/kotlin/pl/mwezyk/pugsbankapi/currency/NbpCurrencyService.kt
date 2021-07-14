package pl.mwezyk.pugsbankapi.currency

import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

@Service
class NbpCurrencyService(currencyClient: NbpCurrencyClient) : CurrencyService {

    private val client = currencyClient;

    override fun convertToCurrency(amount: Long, currencyCode: CurrencyCode): String {
        val inDollars = getFormattedAmount(amount)
        return getToDollarsFormatter().format(inDollars)
    }

    private fun getFormattedAmount(amount: Long): BigDecimal {
        val multiplier = BigDecimal(100)
        val bigAmount = BigDecimal(amount)
        val exchangeRate = BigDecimal(getExchangeRate())

        return bigAmount.divide(multiplier, 2, RoundingMode.HALF_UP).divide(exchangeRate, 2, RoundingMode.HALF_UP)
    }

    private fun getToDollarsFormatter(): NumberFormat {
        val format = NumberFormat.getCurrencyInstance(Locale.US)
        format.maximumFractionDigits = 2
        format.currency = Currency.getInstance(CurrencyCode.USD.name)
        return format;
    }

    override fun getExchangeRate(currencyCode: CurrencyCode): String {
        val response = client.getExchangeRate(currencyCode) ?: throw EmptyExchangeRatesException("Empty exchange rate list, not able to process")

        val exchangeRate = response.rates.first().currencyRate.exchangeRate
        return exchangeRate
    }

    class EmptyExchangeRatesException(message: String): RuntimeException(message)

}