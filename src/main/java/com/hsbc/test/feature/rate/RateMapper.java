package com.hsbc.test.feature.rate;

import com.hsbc.test.integration.exchangerates.RateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RateMapper {
    RateMapper INSTANCE = Mappers.getMapper( RateMapper.class );

    RateE dtoToEntity(RateDto source);
    RateDto entityToDto(RateE source);
}
