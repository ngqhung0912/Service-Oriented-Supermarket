package com.project.authentication.entity;

import lombok.Getter;
import lombok.Setter;

public class NewUserData {

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
