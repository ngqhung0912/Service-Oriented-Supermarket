package com.project.supermarketapigateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.node.BigIntegerNode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)

// The following two annotations are from lombok (https://projectlombok.org/)
// They automatically generate Getters and Setters for the objects.
@Getter
@Setter

public class UserDataDTO {
    private String email;
    private String name;

}
