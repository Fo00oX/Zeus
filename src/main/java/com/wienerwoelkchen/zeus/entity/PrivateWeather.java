package com.wienerwoelkchen.zeus.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrivateWeather implements Serializable {

    @Id
    private Double temp;
    private Integer humidity;
}