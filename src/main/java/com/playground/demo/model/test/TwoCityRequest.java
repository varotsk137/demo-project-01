package com.playground.demo.model.test;

import com.playground.demo.model.request.CityRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwoCityRequest implements Serializable {
    private CityRequest cityRequestReal;
    private CityRequest cityRequestFake;
}