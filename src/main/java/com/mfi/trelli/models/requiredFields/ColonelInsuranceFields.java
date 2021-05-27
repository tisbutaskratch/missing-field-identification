package com.mfi.trelli.models.requiredFields;

import com.mfi.trelli.models.Address;
import com.mfi.trelli.models.Gender;
import com.mfi.trelli.models.Name;

import java.util.Date;
import java.util.List;

public class ColonelInsuranceFields {
    /**
     * Address of the policy holder
     */
    private Address address;

    /**
     * Name of the policy holder
     */
    private Name name;

    /**
     * The email address of the policy holder
     */
    private String email;

    /**
     * The complete driver’s license number of the policy holder
     */
    private String driversLicenseNumber;

    /**
     * The exact date of birth of all operators
     */
    private List<Date> datesOfBirth;

    /**
     * The gender of all operators
     */
    private List<Gender> genders;
}
