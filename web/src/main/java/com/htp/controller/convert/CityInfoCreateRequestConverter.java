package com.htp.controller.convert;

import com.htp.controller.request.CityInfoCreateRequest;
import com.htp.domain.HibernateCityInfo;
import org.springframework.stereotype.Component;

@Component
public class CityInfoCreateRequestConverter extends CityInfoRequestConverter<CityInfoCreateRequest, HibernateCityInfo> {

    @Override
    public HibernateCityInfo convert(CityInfoCreateRequest source) {
        HibernateCityInfo hibernateCityInfo = new HibernateCityInfo();
        return doConvert(hibernateCityInfo,source);
    }
}
