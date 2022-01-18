package com.playground.demo.model.test;

import com.playground.demo.model.RequestCity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwoRequestCity implements Serializable {
    private RequestCity requestCityReal;
    private RequestCity requestCityFake;
}