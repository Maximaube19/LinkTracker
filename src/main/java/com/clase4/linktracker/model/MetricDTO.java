package com.clase4.linktracker.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Maxi Maubecin
 */
@Getter @Setter
public class MetricDTO {
    private Integer redirectCount;

    public MetricDTO(Integer redirectCount) {
        this.redirectCount = redirectCount;
    }
}
