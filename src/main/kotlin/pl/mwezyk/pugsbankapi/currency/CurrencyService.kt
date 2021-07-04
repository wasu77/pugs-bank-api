package pl.mwezyk.pugsbankapi.currency

interface CurrencyService {
    fun getExchangeRate(currency: Currency) : Double
}