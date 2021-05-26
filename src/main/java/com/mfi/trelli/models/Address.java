package com.mfi.trelli.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
     * Street type for policy holder’s address
     */
    private String type;

    /**
     * Secondary unit type for policy holder’s address
     */
    private SecUnitType sec_unit_type;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public SecUnitType getSec_unit_type() {
        return sec_unit_type;
    }

    public void setSec_unit_type(final SecUnitType sec_unit_type) {
        this.sec_unit_type = sec_unit_type;
    }

    public int getSec_unit_num() {
        return sec_unit_num;
    }

    public void setSec_unit_num(final int sec_unit_num) {
        this.sec_unit_num = sec_unit_num;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(final int zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Address address = (Address) o;

        return new EqualsBuilder().append(number, address.number).append(sec_unit_num, address.sec_unit_num).append(zip, address.zip).append(street, address.street)
                                  .append(type, address.type).append(sec_unit_type, address.sec_unit_type).append(city, address.city).append(state, address.state)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(number).append(street).append(type).append(sec_unit_type).append(sec_unit_num).append(city).append(state).append(zip).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", number)
                .append("street", street)
                .append("type", type)
                .append("sec_unit_type", sec_unit_type)
                .append("sec_unit_num", sec_unit_num)
                .append("city", city)
                .append("state", state)
                .append("zip", zip)
                .toString();
    }
}
