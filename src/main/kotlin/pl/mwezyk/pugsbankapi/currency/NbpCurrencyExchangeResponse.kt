package pl.mwezyk.pugsbankapi.currency

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "ExchangeRatesSeries")
@XmlAccessorType(XmlAccessType.FIELD)
data class NbpCurrencyExchangeResponse(@JacksonXmlProperty(localName = "Code")
                                       val currencyCode: String,
                                       @JacksonXmlProperty(localName = "Rates")
                                       @JacksonXmlElementWrapper(useWrapping = false)
                                       val rates: List<CurrencyRates>)

@XmlRootElement(name = "Rates")
data class CurrencyRates(@JacksonXmlProperty(localName = "Rate") val currencyRate: CurrencyRate)

@XmlRootElement(name = "Rate")
data class CurrencyRate(@JacksonXmlProperty(localName = "Mid") val exchangeRate: String)

