package com.wienerwoelkchen.zeus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Token {
    @Id
    private String token;
}
