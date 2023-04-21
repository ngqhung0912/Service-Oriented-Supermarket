package com.project.supermarketapigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class NewUserDataDTO {

    @Setter
    @Getter
    String id;

    @Getter
    @Setter
    String address;

    @Getter
    @Setter
    int age;

    @Getter
    @Setter
    String username;

    @Getter
    @Setter
    String email;

    @Getter
    @Setter
    String name;

    public String toString() {
        return name + " " + username + " " + email + " " + address + " " + age;
    }

}
