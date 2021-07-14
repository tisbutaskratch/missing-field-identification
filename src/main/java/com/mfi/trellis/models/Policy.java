package com.mfi.trellis.models;

import java.util.Date;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
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
}
