package com.mfi.trelli.models;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith (MockitoJUnitRunner.class)
public class PolicyTest {
    private static final String ISSUER = "libertymutual";

    private Policy policy;

    @Before
    public void setup() {
        policy = new Policy();
    }
    @Test
    public void setAndGetPolicyId() {
        final String POLICY_ID = "129f90fd-c3e6-4e83-bfe5-1b3e3f81a2cd";
        policy.setPolicyId(POLICY_ID);
        assertEquals(POLICY_ID, policy.getPolicyId());
    }

    @Test
    public void setAndGetIssuer() {
        policy.setIssuer(ISSUER);
        assertEquals(ISSUER, policy.getIssuer());
    }

    @Test
    public void setAndGetIssueDate() throws ParseException {
        final Date ISSUE_DATE = new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-23");
        policy.setIssueDate(ISSUE_DATE);
        assertEquals(ISSUE_DATE, policy.getIssueDate());
    }

    @Test
    public void setAndGetRenewalDate() throws ParseException {
        final Date RENEWAL_DATE = new SimpleDateFormat("yyyy-MM-dd").parse("2021-04-23");
        policy.setRenewalDate(RENEWAL_DATE);
        assertEquals(RENEWAL_DATE, policy.getRenewalDate());
    }

    @Test
    public void setAndGetPolicyTermMonths() {
        policy.setPolicyTermMonths(Duration.six);
        assertEquals(Duration.six, policy.getPolicyTermMonths());
    }

    @Test
    public void setAndGetPremiumCents() {
        final float PREMIUM_CENTS = 61259f;
        policy.setPremiumCents(PREMIUM_CENTS);
        assertEquals(PREMIUM_CENTS, policy.getPremiumCents(), 0);
    }

    @Test
    public void setAndGetPolicyHolder() {
        final PolicyHolder POLICY_HOLDER = new PolicyHolder();
        policy.setPolicyHolder(POLICY_HOLDER);
        assertEquals(POLICY_HOLDER, policy.getPolicyHolder());
    }

    @Test
    public void setAndGetOperators() {
        final List<Operator> OPERATORS = new ArrayList<>();
        policy.setOperators(OPERATORS);
        assertEquals(OPERATORS, policy.getOperators());
    }

    @Test
    public void testHashCode() {
        assertEquals(policy.hashCode(), -779599631);
    }

    @Test
    public void testToString() {
        policy.setIssuer(ISSUER);
        assertThat(policy.toString(), CoreMatchers.containsString(ISSUER));
    }
}