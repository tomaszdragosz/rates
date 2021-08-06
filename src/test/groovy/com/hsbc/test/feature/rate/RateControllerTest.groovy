package com.hsbc.test.feature.rate


import com.hsbc.test.BaseTest
import com.hsbc.test.integration.exchangerates.RateDto

import java.time.format.DateTimeFormatter 

class RateControllerTest extends BaseTest {

    def "Should not get not existing rate without loading"() {
        when:
        def response = testApiClient.getForResponse(url: "/rates/2021-08-05?currency=USD")

        then:
        response.status == 200
        response.contentAsString == ""
    }

    def "Should load rates and read current rate"() {
        when:
        def response = testApiClient.getForResponse(url: "/rates/load")

        then:
        response.status == 200

        when:
        def date = java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        response = testApiClient.getForResponse(url: "/rates/$date?currency=USD")

        then:
        response.status == 200
        RateDto rate = new com.google.gson.Gson().fromJson(response.contentAsString, RateDto)
        rate.date == date
        rate.baseCurrency.currencyCode == "EUR"
        rate.currency.currencyCode == "USD"
        rate.rate.doubleValue() > 0d
    }
}
