package com.mfi.trellis.main;

import com.mfi.trellis.models.Address;
import com.mfi.trellis.models.BirthdayRange;
import com.mfi.trellis.models.Name;
import com.mfi.trellis.models.Operator;
import com.mfi.trellis.models.Policy;
import com.mfi.trellis.models.PolicyHolder;
import com.mfi.trellis.enums.Gender;
import com.mfi.trellis.enums.InsuranceType;
import com.mfi.trellis.enums.LicenseStatus;
import com.mfi.trellis.logic.PolicyReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) throws ParseException, IOException, org.json.simple.parser.ParseException {
        //white box test per policy
        int iterator = 0;
        while (25 != iterator) {
            System.out.println(Arrays.toString(PolicyReader.retrieveRequiredFields(parsePoliciesJsonFile().get(iterator),
                                                                                   InsuranceType.TheColonelInsurance).toArray()));
            iterator++;
        }
    }

    private static List<Policy> parsePoliciesJsonFile() throws ParseException, IOException, org.json.simple.parser.ParseException {
        final List<Policy> policies = new ArrayList<>();
        final JSONParser jsonParser = new JSONParser();
        final JSONArray policiesJsonArray = (JSONArray) jsonParser.parse(new FileReader("policies.json"));
        for (final Object policyObject : policiesJsonArray) {
            final JSONObject policyJsonObject = (JSONObject) policyObject;

            final Policy policy = new Policy();
            policy.setPolicyId((String) policyJsonObject.get("policyId"));
            policy.setIssuer((String) policyJsonObject.get("issuer"));
            policy.setIssueDate(new SimpleDateFormat("yyyy-MM-DD", Locale.ENGLISH)
                                        .parse((String) policyJsonObject.get("issueDate")));
            policy.setRenewalDate(new SimpleDateFormat("yyyy-MM-DD", Locale.ENGLISH)
                                          .parse((String) policyJsonObject.get("renewalDate")));
            policy.setPolicyTermMonths((Long) policyJsonObject.get("policyTermMonths"));
            policy.setPremiumCents((Long) policyJsonObject.get("premiumCents"));

            final JSONObject policyHolderJsonObject = (JSONObject) policyJsonObject.get("policyHolder");
            final PolicyHolder policyHolder = new PolicyHolder();
            final JSONObject policyHolderNameJsonObject = (JSONObject) policyHolderJsonObject.get("name");
            final Name policyHolderName = new Name();
            policyHolderName.setFirstName((String) policyHolderNameJsonObject.get("firstName"));
            policyHolderName.setMiddleName((String) policyHolderNameJsonObject.get("middleName"));
            policyHolderName.setLastName((String) policyHolderNameJsonObject.get("lastName"));
            policyHolder.setName(policyHolderName);

            final JSONObject policyHolderAddressJsonObject = (JSONObject) policyHolderJsonObject.get("address");
            final Address policyHolderAddress = new Address();

            final String number = (String) policyHolderAddressJsonObject.get("number");
            if (null != number) {
                policyHolderAddress.setNumber(Integer.parseInt(number));
            }
            policyHolderAddress.setStreet((String) policyHolderAddressJsonObject.get("street"));
            policyHolderAddress.setType((String) policyHolderAddressJsonObject.get("type"));
            policyHolderAddress.setSec_unit_type((String) policyHolderAddressJsonObject.get("sec_unit_type"));

            final String sec_unit_num = (String) policyHolderAddressJsonObject.get("sec_unit_num");
            if (null != sec_unit_num) {
                policyHolderAddress.setSec_unit_num(Integer.parseInt(sec_unit_num));
            }
            policyHolderAddress.setCity((String) policyHolderAddressJsonObject.get("city"));
            policyHolderAddress.setState((String) policyHolderAddressJsonObject.get("state"));

            final String zip = (String) policyHolderAddressJsonObject.get("zip");
            if (null != zip) {
                policyHolderAddress.setZip(Integer.parseInt(zip));
            }
            policyHolder.setAddress(policyHolderAddress);

            policyHolder.setEmail((String) policyHolderJsonObject.get("email"));
            policyHolder.setPhoneNumber((String) policyHolderJsonObject.get("phoneNumber"));
            policy.setPolicyHolder(policyHolder);

            final JSONArray operatorsJsonArray = (JSONArray) policyJsonObject.get("operators");
            final List<Operator> operators = new ArrayList<>();
            for (final Object operatorObject : operatorsJsonArray) {
                final JSONObject operatorJsonObject = (JSONObject) operatorObject;
                final Operator operator = new Operator();
                operator.setPrimary((Boolean) operatorJsonObject.get("isPrimary"));

                final JSONObject operatorNameJsonObject = (JSONObject) operatorJsonObject.get("name");
                final Name operatorName = new Name();
                operatorName.setFirstName((String) operatorNameJsonObject.get("firstName"));
                operatorName.setMiddleName((String) operatorNameJsonObject.get("middleName"));
                operatorName.setLastName((String) operatorNameJsonObject.get("lastName"));
                operator.setName(operatorName);

                final JSONObject operatorBirthdayRangeJsonObject = (JSONObject) operatorJsonObject.get("birthdayRange");
                final BirthdayRange operatorBirthdayRange = new BirthdayRange();

                final String start = (String) operatorBirthdayRangeJsonObject.get("start");
                if (null != start) {
                    operatorBirthdayRange.setStart(new SimpleDateFormat("yyyy-MM-DD", Locale.ENGLISH)
                                                           .parse(start));
                }

                final String end = (String) operatorBirthdayRangeJsonObject.get("end");
                if (null != end) {
                    operatorBirthdayRange.setEnd(new SimpleDateFormat("yyyy-MM-DD", Locale.ENGLISH)
                                                         .parse(end));
                }
                operator.setBirthdayRange(operatorBirthdayRange);

                final String gender = (String) operatorJsonObject.get("gender");
                if (null != gender) {
                    operator.setGender(Gender.valueOf(gender));
                }
                operator.setDriversLicenseState((String) operatorJsonObject.get("driversLicenseState"));

                final String driversLicenseStatus = (String) operatorJsonObject.get("driversLicenseStatus");
                if (null != driversLicenseStatus) {
                    operator.setDriversLicenseStatus(LicenseStatus.valueOf(driversLicenseStatus));
                }
                operator.setDriversLicenseNumber((String) operatorJsonObject.get("driversLicenseNumber"));
                operator.setRelationship((String) operatorJsonObject.get("relationship"));
                operators.add(operator);
            }
            policy.setOperators(operators);

            policies.add(policy);
        }
        return policies;
    }
}
