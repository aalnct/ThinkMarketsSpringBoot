package com.thinkmarkets.ThinkMarketsSpringBoot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PersonDTO implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("phone")
    private int phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
