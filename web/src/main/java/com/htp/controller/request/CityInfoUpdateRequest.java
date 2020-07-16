package com.htp.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityInfoUpdateRequest extends CityInfoCreateRequest{

    private Long id;
}
