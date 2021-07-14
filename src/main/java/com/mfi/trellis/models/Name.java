package com.mfi.trellis.models;

import lombok.Data;

@Data
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
