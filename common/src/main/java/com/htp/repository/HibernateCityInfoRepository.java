package com.htp.repository;

import com.htp.domain.HibernateCityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HibernateCityInfoRepository extends CrudRepository<HibernateCityInfo,Long>,
        JpaRepository<HibernateCityInfo,Long> {

    @Override
    Optional<HibernateCityInfo> findById(Long aLong);

    @Query("SELECT hci.info FROM HibernateCityInfo hci WHERE hci.capital=:capital")
    String findInfoByCapital(String capital);

    @Query("SELECT hci.capital FROM HibernateCityInfo hci ")
    List<String> findAllCapitals();

    @Query("SELECT hci.country FROM HibernateCityInfo hci ")
    List<String> findAllCountries();
}
