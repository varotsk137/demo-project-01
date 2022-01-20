package com.playground.demo.model.response;

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
public class DataResponse implements Serializable {

    private String city;

    private String country;

    private List<PopulationCountsResponse> populationCounts;

}
