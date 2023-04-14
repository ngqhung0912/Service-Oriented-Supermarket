package com.project.supermarketapigateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)

// The following two annotations are from lombok (https://projectlombok.org/)
// They automatically generate Getters and Setters for the objects.
@Getter
@Setter

public class UserDataDTO {
    private String email;
    private String name;

}
