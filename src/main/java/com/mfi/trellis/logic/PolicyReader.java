package com.mfi.trellis.logic;

import com.mfi.trellis.models.Address;
import com.mfi.trellis.models.BirthdayRange;
import com.mfi.trellis.models.Name;
import com.mfi.trellis.models.Operator;
import com.mfi.trellis.models.Policy;
import com.mfi.trellis.models.PolicyHolder;
import com.mfi.trellis.enums.InsuranceType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Read stored data from the policy.
 */
public class PolicyReader {
    /**
     * Retrieve fields that are required and missing
     * @param policy object containing date per policy. Including policy holder and operators information.
     * @param insuranceType type of insurance
     * @return list of fields that are missing data
     */
    public static List<String> retrieveRequiredFields(final Policy policy, final InsuranceType insuranceType) {
        switch (insuranceType) {
            case TheColonelInsurance:
                return retrieveRequiredFieldsFromPolicyTCI(policy);
            case RanchersInsurance:
                //logic for Ranchers Insurance would go here
                break;
            default:
                break;
        }
        return null;
    }

    /**
     * Retrieve list of fields missing from stored policy information for The Colonel Insurance.
     * This list includes:
     * <ul>
     *   <li>Address of the policy holder</li>
     *   <li>Name of the policy holder</li>
     *   <li>Email address of the policy holder</li>
     *   <li>Complete driver’s license number of the policy holder</li>
     *   <li>Exact date of birth of all operators</li>
     *   <li>Gender of all operators</li>
     * </ul>
     * @param policy object containing date per policy. Including policy holder and operators information.
     * @return list of fields that are missing data
     */
    private static List<String> retrieveRequiredFieldsFromPolicyTCI(final Policy policy) {
        final List<String> missingRequiredFields = new ArrayList<>();
        final PolicyHolder policyHolder = policy.getPolicyHolder();
        final List<Operator> operators = policy.getOperators(); //assuming policy holder will always have an operator
        if (!isAddressOfPolicyHolderPresent(policyHolder)) {
            missingRequiredFields.add("Address");
        }

        if (!isNameOfPolicyHolderPresent(policyHolder)) {
            missingRequiredFields.add("Name");
        }

        if (!isEmailAddressOfPolicyHolderPresent(policyHolder)) {
            missingRequiredFields.add("EmailAddress");
        }

        if (!isDriverLicenseNumberOfPolicyHolderPresent(operators)) {
            missingRequiredFields.add("DriverLicenseNumber");
        }

        final List<String> operatorsWithMissingExactBirthDate = retrieveOperatorsWithMissingExactBirthDate(operators);
        if (!operatorsWithMissingExactBirthDate.isEmpty()) {
            for (final String operatorName: operatorsWithMissingExactBirthDate) {
                missingRequiredFields.add("ExactBirthDateOfOperator: "  + operatorName);
            }
        }

        final List<String> operatorsWithMissingGender = retrieveOperatorsWithMissingGender(operators);
        if (!operatorsWithMissingGender.isEmpty()) {
            for (final String operatorName : operatorsWithMissingGender) {
                missingRequiredFields.add("GenderOfOperator: " + operatorName);
            }
        }
        return missingRequiredFields;
    }

    /**
     * Check if the policy holder's address street, number, type, city and zip are present
     * @param policyHolder object containing the policy holder's information
     * @return true if present; false otherwise
     */
    private static boolean isAddressOfPolicyHolderPresent(final PolicyHolder policyHolder) {
        final Address policyHolderAddress = policyHolder.getAddress();
        // the address and primary fields have to be non-null. The primary fields also have to be not empty or 0.
        if (null == policyHolderAddress) {
            return false;
        }

        final String policyHolderAddressStreet = policyHolderAddress.getStreet();
        if (null == policyHolderAddressStreet || "".equals(policyHolderAddressStreet.trim())) {
            return false;
        }

        final String policyHolderAddressType = policyHolderAddress.getType();
        if (null == policyHolderAddressType || "".equals(policyHolderAddressType.trim())) {
            return false;
        }

        final String policyHolderAddressCity = policyHolderAddress.getCity();
        if (null == policyHolderAddressCity || "".equals(policyHolderAddressCity.trim())) {
            return false;
        }
        return 0 != policyHolderAddress.getZip() && 0 != policyHolderAddress.getNumber();
    }

    /**
     * Check if the policy holder's first name and last name are present
     * @param policyHolder object containing the policy holder's information
     * @return true if present; false otherwise
     */
    private static boolean isNameOfPolicyHolderPresent(final PolicyHolder policyHolder) {
        final Name policyHolderName = policyHolder.getName();
        // the name, first name, last name have to be non-null. The first and last name also need to be not empty.
        if (null == policyHolderName) {
            return false;
        }

        final String firstName = policyHolderName.getFirstName();
        if (null == firstName || "".equals(firstName.trim())) {
            return false;
        }

        final String lastName = policyHolderName.getLastName();
        return null != lastName && !"".equals(lastName.trim());
    }

    /**
     * Check if the policy holder's email is present
     * @param policyHolder object containing the policy holder's information
     * @return true if present; false otherwise
     */
    private static boolean isEmailAddressOfPolicyHolderPresent(final PolicyHolder policyHolder) {
        final String policyHolderEmail = policyHolder.getEmail();
        // if the email is null or empty or does not have an @, then it is required.
        return (null != policyHolderEmail && !"".equals(policyHolderEmail) && policyHolderEmail.contains("@"));
    }

    /**
     * Check if the policy holder's license number is present
     * @param operators authorized operators for the policy
     * @return true if present; false otherwise
     */
    private static boolean isDriverLicenseNumberOfPolicyHolderPresent(final List<Operator> operators) {
        // a primary operator is the policy holder. If the license number is null or has XXXXX or ***** then it is incomplete.
        final String policyHolderDriverLicenseNumber = operators.stream()
                                                                .filter(Operator::isPrimary)
                                                                .map(Operator::getDriversLicenseNumber)
                                                                .map(driversLicenseNumber -> driversLicenseNumber !=null ? driversLicenseNumber : "")
                                                                .findFirst()
                                                                .orElse(null);
        return null != policyHolderDriverLicenseNumber && !"".equals(policyHolderDriverLicenseNumber.trim()) && !policyHolderDriverLicenseNumber.contains("XXXXX")
               && !policyHolderDriverLicenseNumber.contains("*****");
    }

    /**
     * Retrieve all operators whose birthdate is not exact. A birthdate is exact when the start and end range are a match.
     * @param operators authorized operators for the policy
     * @return list of operators
     */
    private static List<String> retrieveOperatorsWithMissingExactBirthDate(final List<Operator> operators) {
        final List<String> operatorIdentities = new ArrayList<>();
        for (final Operator operator : operators) {
            final BirthdayRange operatorBirthdayRange = operator.getBirthdayRange();
            if (null == operatorBirthdayRange) {
                final Name operatorName = operator.getName();
                operatorIdentities.add(operatorName.getFirstName() + " " + operatorName.getLastName());
                continue;
            }

            final Date operatorBirthdayStartRange = operatorBirthdayRange.getStart();
            final Date operatorBirthdayEndRange = operatorBirthdayRange.getEnd();
            if (null == operatorBirthdayStartRange ||
                !operatorBirthdayStartRange.equals(operatorBirthdayEndRange)) {
                final Name operatorName = operator.getName();
                operatorIdentities.add(operatorName.getFirstName() + " " + operatorName.getLastName());
            }
        }
        return operatorIdentities;
    }

    /**
     * Retrieve all operators whose gender is not present.
     * @param operators authorized operators for the policy
     * @return list of operators
     */
    private static List<String> retrieveOperatorsWithMissingGender(final List<Operator> operators) {
        final List<String> operatorIdentities = new ArrayList<>();
        for (final Operator operator : operators) {
            if (null == operator.getGender()) {
                final Name operatorName = operator.getName();
                operatorIdentities.add(operatorName.getFirstName() + " " + operatorName.getLastName());
            }
        }
        return operatorIdentities;
    }
}
