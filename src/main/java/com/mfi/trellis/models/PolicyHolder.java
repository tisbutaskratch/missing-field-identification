package com.mfi.trellis.models;

import lombok.Data;

@Data
public class PolicyHolder {
    private Name name;

    private Address address;

    private String email;

    private String phoneNumber;
}
