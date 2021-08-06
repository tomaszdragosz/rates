package com.hsbc.test.feature.rate;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Currency;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class RateE {
    @Id
    @GeneratedValue
    private long id;
    private Currency currency;
    private Currency baseCurrency;
    private BigDecimal rate;
    private String date;
}
