package com.hsbc.test.feature.rate;

import com.hsbc.test.integration.exchangerates.ExchangeratesapiClient;
import com.hsbc.test.integration.exchangerates.RateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rates")
@RequiredArgsConstructor
public class RateController {

    private final ExchangeratesapiClient exchangeratesapiClient;
    private final RateRepository rateRepository;

    @GetMapping("load")
    public void loadRates() {
        RateMapper rateMapper = RateMapper.INSTANCE;
        List<RateDto> rates = exchangeratesapiClient.loadRates();
        rateRepository.saveAll(rates.stream().map(r -> rateMapper.dtoToEntity(r)).collect(Collectors.toList()));
    }

    @GetMapping("/{date}")
    public RateDto getRate(@PathVariable String date, @RequestParam Currency currency) {
        RateMapper rateMapper = RateMapper.INSTANCE;
        RateE rate = rateRepository.findByDateAndCurrency(date, currency);
        return rateMapper.entityToDto(rate);
    }

}
