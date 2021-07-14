package pl.mwezyk.pugsbankapi.currency

interface CurrencyService {
    fun getExchangeRate(currencyCode: CurrencyCode = CurrencyCode.USD) : String
    fun convertToCurrency(amount: Long, currencyCode: CurrencyCode = CurrencyCode.USD) : String
}