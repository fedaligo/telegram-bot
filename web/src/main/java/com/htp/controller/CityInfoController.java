package com.htp.controller;

import com.htp.controller.request.CityInfoCreateRequest;
import com.htp.controller.request.CityInfoUpdateRequest;
import com.htp.domain.HibernateCityInfo;
import com.htp.repository.HibernateCityInfoRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin
@RequestMapping(value = "/cityinfo")
@RequiredArgsConstructor
public class CityInfoController {
    private final ConversionService conversionService;
    private final HibernateCityInfoRepository hibernateCityInfoRepository;

    /*FindAll */
    @ApiOperation(value = "Find all city information")
    @GetMapping("/cityinfo")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateCityInfo>> getAllInfo() {
        return new ResponseEntity<>(hibernateCityInfoRepository.findAll(), HttpStatus.OK);
    }

    /*Create */
    @ApiOperation(value = "Create new city information")
    @PostMapping("/cityinfo")
    @Transactional(rollbackFor = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateCityInfo> createConvertedHibernateCityInfo(@RequestBody @Valid CityInfoCreateRequest request) {
        return new ResponseEntity<>(hibernateCityInfoRepository.saveAndFlush(conversionService.convert(request, HibernateCityInfo.class)), CREATED);
    }

    /*Update*/
    @ApiOperation(value = "Update city information")
    @PutMapping("/cityinfo")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateCityInfo> updateHibernateCityInfoRepository(@RequestBody @Valid CityInfoUpdateRequest request,
                                                                       String city) {
        HibernateCityInfo hibernateCityInfo = hibernateCityInfoRepository.findByCapital(city).
                orElseThrow(() -> new EntityNotFoundException("cityinfo"));
        request.setId(hibernateCityInfo.getId());
        return new ResponseEntity<>(hibernateCityInfoRepository.save(conversionService.convert
                (request, HibernateCityInfo.class)), HttpStatus.OK);
    }

    /*Delete */
    @ApiOperation(value = "Delete city information")
    @DeleteMapping("/cityinfo")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> deleteCityInfo(String city) {
        hibernateCityInfoRepository.deleteCityInfo(city);
        return new ResponseEntity<>("city information is deleted", HttpStatus.OK);
    }
}
