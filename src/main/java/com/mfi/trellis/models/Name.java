package com.mfi.trellis.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Name {
    /**
     * Policy holder’s first name
     */
    private String firstName;

    /**
     * Policy holder’s middle name
     */
    private String middleName;

    /**
     * Policy holder’s last name
     */
    private String lastName;
}
