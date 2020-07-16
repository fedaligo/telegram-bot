package com.htp.controller.convert;

import com.htp.controller.request.CityInfoCreateRequest;
import com.htp.controller.request.CityInfoUpdateRequest;
import com.htp.domain.HibernateCityInfo;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class CityInfoChangeRequestConverter extends CityInfoRequestConverter<CityInfoUpdateRequest, HibernateCityInfo> {

    @Override
    public HibernateCityInfo convert(CityInfoUpdateRequest source) {
        HibernateCityInfo hibernateCityInfo = entityManager.find(HibernateCityInfo.class, source.getId());
        return doConvert(hibernateCityInfo, source);
    }
}
