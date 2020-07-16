package com.htp.controller.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityInfoCreateRequest {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    @ApiModelProperty(example = "Belarus")
    private String country;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    @ApiModelProperty(example = "Minsk")
    private String capital;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 350)
    @ApiModelProperty(example = "Minsk is the capital of Belarus.")
    private String info;
}
