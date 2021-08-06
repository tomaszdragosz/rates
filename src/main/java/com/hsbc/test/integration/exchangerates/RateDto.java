package com.hsbc.test.integration.exchangerates;

import lombok.*;

import java.math.BigDecimal;
import java.util.Currency;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {
    private Currency currency;
    private Currency baseCurrency;
    private BigDecimal rate;
    private String date;
}
