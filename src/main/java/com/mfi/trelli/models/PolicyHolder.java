package com.mfi.trelli.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PolicyHolder {
    private Name name;

    private Address address;

    private String email;

    private String phoneNumber;

    public Name getName() {
        return name;
    }

    public void setName(final Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final PolicyHolder that = (PolicyHolder) o;

        return new EqualsBuilder().append(name, that.name).append(address, that.address).append(email, that.email).append(phoneNumber, that.phoneNumber).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(name).append(address).append(email).append(phoneNumber).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("address", address)
                .append("email", email)
                .append("phoneNumber", phoneNumber)
                .toString();
    }
}
