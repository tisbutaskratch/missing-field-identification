package com.mfi.trellis.logic;

import com.mfi.trelli.models.Address;
import com.mfi.trelli.models.Policy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PolicyReader {
    /**
     * the address of the policy holder
     * the name of the policy holder
     * the email address of the policy holder
     * the complete driver’s license number of the policy holder
     * the exact date of birth of all operators
     * the gender of all operators
     */
    public static List<> readPolicy(final Policy policy) {
        final List<> requirementList = new ArrayList<>();
        isAddressOfPolicyHolderPresent ? continue : requirementList.add(Address);
        return Collections.EMPTY_LIST;
    }

    private static boolean isAddressOfPolicyHolderPresent(final Policy policy) {
        policy.getPolicyHolder().getAddress();
        return false;
    }

    private static boolean isNameOfPolicyHolderPresent(final Policy policy) {
        return false;
    }

    private static boolean isEmailAddressOfPolicyHolderPresent(final Policy policy) {
        return false;
    }

    private static boolean isDriverLicenseNumberOfPolicyHolderPresent(final Policy policy) {
        return false;
    }

    private static boolean isExactBirthdateOfAllOperatorsPresent(final Policy policy) {
        return false;
    }

    private static boolean isGenderOfAllOperatorsPresent(final Policy policy) {
        return false;
    }
}
