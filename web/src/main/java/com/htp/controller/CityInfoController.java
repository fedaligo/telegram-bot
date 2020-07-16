package com.htp.controller;

import com.htp.domain.HibernateCityInfo;
import com.htp.repository.HibernateCityInfoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/cityinfo")
@RequiredArgsConstructor
public class CityInfoController {
    private final HibernateCityInfoRepository hibernateCityInfoRepository;

    @GetMapping("/tt")
    @ResponseStatus(HttpStatus.OK)
    public String getHibernatesCarsRepository() {
        return "trtr";
    }

    /*FindById*/
    @ApiOperation(value = "Get from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Cars"),
            @ApiResponse(code = 400, message = "Invalid Cars ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Cars was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    @GetMapping(value = "/test/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateCityInfo> getHByIdRepository(@ApiParam("Path Id") @PathVariable("id") Long id) {
        HibernateCityInfo t = hibernateCityInfoRepository.findById(id).orElse(null);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @GetMapping(value = "/test/t")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<String>> getAllCapitals() {
        return new ResponseEntity<>(hibernateCityInfoRepository.findAllCapitals(), HttpStatus.OK);
    }
    @GetMapping(value = "/test1/{city}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getInfoByCity(@ApiParam("Path city") @PathVariable("city") String city) {
        String t = hibernateCityInfoRepository.findInfoByCapital(city.substring(0, 1).toUpperCase() + city.substring(1));
        return new ResponseEntity<>(t, HttpStatus.OK);
    }
}
