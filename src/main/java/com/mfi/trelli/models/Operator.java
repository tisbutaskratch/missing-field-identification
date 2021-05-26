package com.mfi.trelli.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Operator {

    /**
     * Is the operator the policy holder
     */
    private boolean isPrimary;

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
    private int driversLicenseNumber;

    /**
     * Operator’s relationship to policyholder
     */
    private String relationship;

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(final boolean primary) {
        isPrimary = primary;
    }

    public Name getName() {
        return name;
    }

    public void setName(final Name name) {
        this.name = name;
    }

    public BirthdayRange getBirthdayRange() {
        return birthdayRange;
    }

    public void setBirthdayRange(final BirthdayRange birthdayRange) {
        this.birthdayRange = birthdayRange;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public LicenseStatus getDriversLicenseStatus() {
        return driversLicenseStatus;
    }

    public void setDriversLicenseStatus(final LicenseStatus driversLicenseStatus) {
        this.driversLicenseStatus = driversLicenseStatus;
    }

    public String getDriversLicenseState() {
        return driversLicenseState;
    }

    public void setDriversLicenseState(final String driversLicenseState) {
        this.driversLicenseState = driversLicenseState;
    }

    public int getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

    public void setDriversLicenseNumber(final int driversLicenseNumber) {
        this.driversLicenseNumber = driversLicenseNumber;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(final String relationship) {
        this.relationship = relationship;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Operator operator = (Operator) o;

        return new EqualsBuilder().append(isPrimary, operator.isPrimary).append(driversLicenseNumber, operator.driversLicenseNumber).append(name, operator.name)
                                  .append(birthdayRange, operator.birthdayRange).append(gender, operator.gender)
                                  .append(driversLicenseStatus, operator.driversLicenseStatus).append(driversLicenseState, operator.driversLicenseState)
                                  .append(relationship, operator.relationship).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(isPrimary).append(name).append(birthdayRange).append(gender).append(driversLicenseStatus).append(driversLicenseState).append(driversLicenseNumber)
                                          .append(relationship).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("isPrimary", isPrimary)
                .append("name", name)
                .append("birthdayRange", birthdayRange)
                .append("gender", gender)
                .append("driversLicenseStatus", driversLicenseStatus)
                .append("driversLicenseState", driversLicenseState)
                .append("driversLicenseNumber", driversLicenseNumber)
                .append("relationship", relationship)
                .toString();
    }
}
