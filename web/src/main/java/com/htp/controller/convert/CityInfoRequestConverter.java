package com.htp.controller.convert;

import com.htp.controller.request.CityInfoCreateRequest;
import com.htp.domain.HibernateCityInfo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CityInfoRequestConverter<S,T> extends EntityConverter<S,T>{

    protected HibernateCityInfo doConvert(HibernateCityInfo hibernateCityInfo, CityInfoCreateRequest request){
        hibernateCityInfo.setCountry(request.getCountry());
        hibernateCityInfo.setCapital(request.getCapital());
        hibernateCityInfo.setInfo(request.getInfo());
        return hibernateCityInfo;
    }
}
