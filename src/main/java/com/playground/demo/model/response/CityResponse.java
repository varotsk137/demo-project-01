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
public class CityResponse implements Serializable {

    private boolean error;

    private String msg;

    private DataResponse data;

}
