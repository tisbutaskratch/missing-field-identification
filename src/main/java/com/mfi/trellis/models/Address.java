package com.mfi.trellis.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Address {
    /**
     * Street number for policy holder’s address
     */
    private int number;

    /**
     * Street name for policy holder’s address
     */
    private String street;

    /**
     * Suffix for policy holder’s address
     */
    private String suffix;

    /**
     * Street type for policy holder’s address
     */
    private String type;

    /**
     * Secondary unit type for policy holder’s address
     */
    private String sec_unit_type;

    /**
     * Secondary unit number for policy holder’s address
     */
    private int sec_unit_num;

    /**
     * City for policy holder’s address
     */
    private String city;

    /**
     * State for policy holder’s address
     */
    private String state;

    /**
     * Zip code for policy holder’s address
     */
    private int zip;

    /**
     * Plus 4 after zipcode for policy holder’s address
     */
    private int plus4;
}
