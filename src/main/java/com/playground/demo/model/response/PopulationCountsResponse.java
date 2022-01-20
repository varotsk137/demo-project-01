package com.playground.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PopulationCountsResponse implements Serializable {

    private String year;

    private String value;

    private String sex;

    private String reliabilty;

}
