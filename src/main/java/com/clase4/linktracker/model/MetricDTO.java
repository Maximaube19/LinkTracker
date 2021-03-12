package com.clase4.linktracker.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MetricDTO {
    private Integer redirectCount;

    public MetricDTO(Integer redirectCount) {
        this.redirectCount = redirectCount;
    }
}
