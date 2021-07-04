package pl.mwezyk.pugsbankapi.currency;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "ExchangeRatesSeries")
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class NbpCurrencyExchangeResponse {

    @JacksonXmlProperty(localName = "Code")
    protected String currencyCode;

    @JacksonXmlProperty(localName = "Rates")
    @JacksonXmlElementWrapper(useWrapping = false)
    protected List<CurrencyRates> rates;

    @Getter
    @Setter
    @XmlRootElement(name = "Rates")
    @NoArgsConstructor
    protected static class CurrencyRates {

        @JacksonXmlProperty(localName = "Rate")
        protected CurrencyRate currencyRate;

    }

    @Getter
    @Setter
    @XmlRootElement(name = "Rate")
    @NoArgsConstructor
    protected static class CurrencyRate {

        @JacksonXmlProperty(localName = "Mid")
        protected String exchangeRate;

    }
}
