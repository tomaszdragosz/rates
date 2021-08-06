package com.hsbc.test.integration.exchangerates;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Rate{
    @JsonProperty("USD")
    public BigDecimal usd;
    @JsonProperty("HKD")
    public BigDecimal hkd;
    @JsonProperty("GBP")
    public BigDecimal gbp;
}
