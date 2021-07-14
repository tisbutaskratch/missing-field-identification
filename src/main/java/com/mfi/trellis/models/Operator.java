package com.mfi.trellis.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mfi.trellis.enums.Gender;
import com.mfi.trellis.enums.LicenseStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Operator {
    /**
     * Is the operator the policy holder
     */
    private Boolean primary;

    /**
     * Operator's name
     */
    private Name name;

    /**
     * Operator's birthday range
     */
    private BirthdayRange birthdayRange;

    /**
     * Operator’s gender
     */
    private Gender gender;

    /**
     * Operator’s driver’s license status
     */
    private LicenseStatus driversLicenseStatus;

    /**
     * Operator’s driver’s license state
     */
    private String driversLicenseState;

    /**
     * Operator’s driver’s license number
     */
    private String driversLicenseNumber;

    /**
     * Operator’s relationship to policyholder
     */
    private String relationship;

    @JsonProperty (value="isPrimary")
    public Boolean isPrimary() {
        return primary;
    }
}
