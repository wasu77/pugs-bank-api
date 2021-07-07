package pl.mwezyk.pugsbankapi.currency

import org.springframework.stereotype.Service
import java.text.NumberFormat
import java.util.*

@Service
class NbpCurrencyService(currencyClient: NbpCurrencyClient) : CurrencyService {

    private val CENTS_IN_DOLLAR = 100
    private val client = currencyClient;

    override fun convertToCurrency(amount: Long, currencyCode: CurrencyCode): String {
        val inDollars = getFormattedAmount(amount)
        return getToDollarsFormatter().format(inDollars)
    }

    private fun getFormattedAmount(amount: Long) = if (amount in (-100 .. 100))
        amount.div(getExchangeRate()).div(CENTS_IN_DOLLAR)
    else
        amount.div(CENTS_IN_DOLLAR).div(getExchangeRate())

    private fun getToDollarsFormatter(): NumberFormat {
        val format = NumberFormat.getCurrencyInstance(Locale.US)
        format.maximumFractionDigits = 2
        format.currency = Currency.getInstance(CurrencyCode.USD.name)
        return format;
    }

    override fun getExchangeRate(currencyCode: CurrencyCode): Double {
        val response = client.getExchangeRate(currencyCode) ?: throw EmptyExchangeRatesException("Empty exchange rate list, not able to process")

        val exchangeRate = response.rates.first().currencyRate.exchangeRate
        return exchangeRate.toDouble()
    }

    class EmptyExchangeRatesException(message: String): RuntimeException(message)

}