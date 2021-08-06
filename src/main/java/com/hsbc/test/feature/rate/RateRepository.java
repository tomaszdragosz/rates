package com.hsbc.test.feature.rate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Currency;

@Repository
public interface RateRepository extends JpaRepository<RateE, Long> {

    RateE findByDateAndCurrency(String date, Currency currency);
}
