package com.mfi.trellis.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

public class Policy {
    /**
     * unique identifier for policy
     */
    private String policyId;

    /**
     * Company that issued the policy,
     */
    private String issuer;

    /**
     * Date policy starts, YYYY-MM-DD
     */
    private Date issueDate;

    /**
     * Date policy ends, YYYY-MM-DD
     */
    private Date renewalDate;

    /**
     * Duration of policy in months, 6 or 12
     */
    private long policyTermMonths;

    /**
     * Total premium (cost) for the term of the policy, in cents
     */
    private long premiumCents;

    /**
     * Policy holder details
     */
    private PolicyHolder policyHolder;

    /**
     * Operators using this policy
     */
    private List<Operator> operators;

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(final String policyId) {
        this.policyId = policyId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(final String issuer) {
        this.issuer = issuer;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(final Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(final Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public long getPolicyTermMonths() {
        return policyTermMonths;
    }

    public void setPolicyTermMonths(final long policyTermMonths) {
        this.policyTermMonths = policyTermMonths;
    }

    public long getPremiumCents() {
        return premiumCents;
    }

    public void setPremiumCents(final long premiumCents) {
        this.premiumCents = premiumCents;
    }

    public PolicyHolder getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(final PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public void setOperators(final List<Operator> operators) {
        this.operators = operators;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Policy policy = (Policy) o;

        return new EqualsBuilder().append(premiumCents, policy.premiumCents).append(policyId, policy.policyId).append(issuer, policy.issuer)
                                  .append(issueDate, policy.issueDate).append(renewalDate, policy.renewalDate).append(policyTermMonths, policy.policyTermMonths)
                                  .append(policyHolder, policy.policyHolder).append(operators, policy.operators).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(policyId).append(issuer).append(issueDate).append(renewalDate).append(policyTermMonths).append(premiumCents).append(policyHolder).append(operators)
                                          .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("policyId", policyId)
                .append("issuer", issuer)
                .append("issueDate", issueDate)
                .append("renewalDate", renewalDate)
                .append("policyTermMonths", policyTermMonths)
                .append("premiumCents", premiumCents)
                .append("policyHolder", policyHolder)
                .append("operators", operators)
                .toString();
    }
}
