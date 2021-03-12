package com.clase4.linktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Maxi Maubecin
 */
@Getter @Setter
public class LinkDTO {
    private Integer linkId;
    private String url;
    @JsonIgnore
    private boolean isValid;
    @JsonIgnore
    private Integer redirectCount;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public LinkDTO() {
        isValid = true;
        redirectCount = 0;
    }


}
