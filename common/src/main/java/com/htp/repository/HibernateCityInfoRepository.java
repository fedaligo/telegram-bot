package com.htp.repository;

import com.htp.domain.HibernateCityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HibernateCityInfoRepository extends CrudRepository<HibernateCityInfo,Long>,
        JpaRepository<HibernateCityInfo,Long> {
    @Override
    Optional<HibernateCityInfo> findById(Long aLong);
}
