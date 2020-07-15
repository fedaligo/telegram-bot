package com.htp.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "city_info")
public class HibernateCityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "country")
    private String country;

    @Column(name = "capital")
    private String capital;

    @Column(name = "info")
    private String info;
}
