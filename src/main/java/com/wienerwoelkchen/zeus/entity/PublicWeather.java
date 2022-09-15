package com.wienerwoelkchen.zeus.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicWeather implements Serializable {

    @Id
    @JsonIgnore
    private Long id;
    private String responseLocation;
    private String description;
    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
}
