package com.mfi.trellis.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PolicyHolder {
    private Name name;

    private Address address;

    private String email;

    private String phoneNumber;
}
