package com.hsbc.test.integration.exchangerates;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeratesapiClient {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${exchangeratesapi.access.key}")
    private String accessKey;
    @Value("${exchangeratesapi.log:false}")
    private boolean logToFile;
    @Value("${exchangeratesapi.days:365}")
    private int days;

    @SneakyThrows
    public List<RateDto> loadRates() {
        LocalDate dateTo = LocalDate.now();
        LocalDate dateFrom = dateTo.minusDays(days);
        LocalDate date = dateTo;
        List<RateDto> result = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            while (dateFrom.isBefore(date)) {
                String dateStr = date.format(formatter);
                ResponseEntity<Rates> response = restTemplate.getForEntity(getUrl(dateStr), Rates.class);
                result.addAll(mapToRatesDto(response.getBody()));
                date = date.minusDays(1);
            }
        } catch (Exception e) {
            log.warn("exchangeratesapi not available. Using stored file.", e);
            Gson gson = new Gson();
            Type type = new TypeToken<List<RateDto>>() {
            }.getType();
            result = gson.fromJson(new JsonReader(new FileReader("src/main/resources/rates.json")), type);
        }

        if (logToFile) {
            storeInFile(result);
        }
        return result;
    }

    private String getUrl(String dateStr) {
        return "http://api.exchangeratesapi.io/v1/" + dateStr + "?access_key=" + accessKey + "&base=EUR&symbols=GBP,USD,HKD";
    }

    @SneakyThrows
    private void storeInFile(List<RateDto> rates) {
        Gson gson = new Gson();
        String json = gson.toJson(rates);
        FileWriter fw = new FileWriter("rates.json");
        fw.append(json);
        fw.close();
    }

    private List<RateDto> mapToRatesDto(Rates body) {
        List<RateDto> result = new ArrayList<>();
        result.add(RateDto.builder()
                .baseCurrency(Currency.getInstance("EUR"))
                .currency(Currency.getInstance("USD"))
                .date(body.date)
                .rate(body.rates.usd)
                .build());
        result.add(RateDto.builder()
                .baseCurrency(Currency.getInstance("EUR"))
                .currency(Currency.getInstance("GBP"))
                .date(body.date)
                .rate(body.rates.gbp)
                .build());
        result.add(RateDto.builder()
                .baseCurrency(Currency.getInstance("EUR"))
                .currency(Currency.getInstance("HKD"))
                .date(body.date)
                .rate(body.rates.hkd)
                .build());
        return result;
    }
}
