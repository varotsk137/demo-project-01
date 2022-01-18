package com.playground.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData implements Serializable {

    private String city;

    private String country;

    private List<ResponsePopulationCounts> populationCounts;

}
