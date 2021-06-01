package com.mfi.trellis.logic;

import com.mfi.trellis.models.Address;
import com.mfi.trellis.models.BirthdayRange;
import com.mfi.trellis.models.Name;
import com.mfi.trellis.models.Operator;
import com.mfi.trellis.models.Policy;
import com.mfi.trellis.models.PolicyHolder;
import com.mfi.trellis.enums.Gender;
import com.mfi.trellis.enums.InsuranceType;
import com.mfi.trellis.enums.LicenseStatus;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PolicyReaderTest {
    Policy policy;
    PolicyHolder policyHolder;
    List<Operator> operators;
    Name policyHolderName;
    Name secondaryOperatorName;
    Address policyHolderAddress;
    BirthdayRange policyHolderBirthdayRange;
    BirthdayRange secondaryOperatorBirthdayRange;
    Operator policyHolderOperator;
    Operator secondaryOperator;

    @Before
    public void setUp() throws ParseException {
        policy = new Policy();
        policyHolder = new PolicyHolder();
        policyHolderName = new Name();
        secondaryOperatorName = new Name();
        policyHolderAddress = new Address();
        policyHolderBirthdayRange = new BirthdayRange();
        secondaryOperatorBirthdayRange = new BirthdayRange();
        policyHolderOperator = new Operator();
        secondaryOperator = new Operator();
        operators = new ArrayList<>();

        policy.setPolicyId("9afe73e5-77c9-4677-ba5f-1b9e807396a0");
        policy.setIssuer("nationwide");
        policy.setIssueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-22"));
        policy.setRenewalDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-23"));
        policy.setPolicyTermMonths(6);
        policy.setPremiumCents(49234);

        policyHolderName.setFirstName("Caroline");
        policyHolderName.setMiddleName("Lisa");
        policyHolderName.setLastName("White");

        policyHolderAddress.setNumber(151);
        policyHolderAddress.setStreet("Ridge");
        policyHolderAddress.setType("Ave");
        policyHolderAddress.setSec_unit_type("Ave");
        policyHolderAddress.setSec_unit_num(360);
        policyHolderAddress.setCity("Metzside");
        policyHolderAddress.setState("TX");
        policyHolderAddress.setZip(38216);

        policyHolder.setName(policyHolderName);
        policyHolder.setAddress(policyHolderAddress);
        policyHolder.setEmail("Caroline.White897@me.com");
        policyHolder.setPhoneNumber("(929) 828-2880");
        policy.setPolicyHolder(policyHolder);

        policyHolderOperator.setPrimary(true);
        policyHolderOperator.setName(policyHolderName);
        policyHolderBirthdayRange.setStart(new SimpleDateFormat("yyyy-MM-dd").parse("1961-03-14"));
        policyHolderBirthdayRange.setEnd(new SimpleDateFormat("yyyy-MM-dd").parse("1961-03-14"));
        policyHolderOperator.setBirthdayRange(policyHolderBirthdayRange);
        policyHolderOperator.setGender(Gender.female);
        policyHolderOperator.setDriversLicenseStatus(LicenseStatus.ValidUSLicense);
        policyHolderOperator.setDriversLicenseState("TX");
        policyHolderOperator.setDriversLicenseNumber("DL094139");
        policyHolderOperator.setRelationship("Named Insured");
        operators.add(policyHolderOperator);

        secondaryOperator.setPrimary(false);
        secondaryOperatorName.setFirstName("Mateo");
        secondaryOperatorName.setMiddleName("");
        secondaryOperatorName.setLastName("Nguyen");
        secondaryOperator.setName(secondaryOperatorName);
        secondaryOperatorBirthdayRange.setStart(new SimpleDateFormat("yyyy-MM-dd").parse("1956-12-31"));
        secondaryOperatorBirthdayRange.setEnd(new SimpleDateFormat("yyyy-MM-dd").parse("1956-12-31"));
        secondaryOperator.setBirthdayRange(secondaryOperatorBirthdayRange);
        secondaryOperator.setGender(Gender.male);
        secondaryOperator.setDriversLicenseStatus(LicenseStatus.ValidUSLicense);
        secondaryOperator.setDriversLicenseState("KS");
        secondaryOperator.setDriversLicenseNumber("DL062629");
        secondaryOperator.setRelationship("Child");
        operators.add(secondaryOperator);
        policy.setOperators(operators);
    }

    @Test
    public void testRetrieveRequiredFieldsTCINoRequiredFields() {
        assertTrue(PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).isEmpty());
    }

    @Test
    public void testRetrieveRequiredFieldsRanchersInsurance() {
        assertNull(PolicyReader.retrieveRequiredFields(policy, InsuranceType.RanchersInsurance));
    }

    @Test
    public void testRetrieveRequiredFieldsDefault() {
        assertNull(PolicyReader.retrieveRequiredFields(policy, InsuranceType.SomeOtherInsurance));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullAddress() {
        policy.getPolicyHolder().setAddress(null);
        assertEquals("Address", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullStreet() {
        policy.getPolicyHolder().getAddress().setStreet(null);
        assertEquals("Address", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCIEmptyStreet() {
        policy.getPolicyHolder().getAddress().setStreet("   ");
        assertEquals("Address", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullType() {
        policy.getPolicyHolder().getAddress().setType(null);
        assertEquals("Address", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCIEmptyType() {
        policy.getPolicyHolder().getAddress().setType("   ");
        assertEquals("Address", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullCity() {
        policy.getPolicyHolder().getAddress().setCity(null);
        assertEquals("Address", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCIEmptyCity() {
        policy.getPolicyHolder().getAddress().setCity("   ");
        assertEquals("Address", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINoZip() {
        policy.getPolicyHolder().getAddress().setZip(0);
        assertEquals("Address", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINoNumber() {
        policy.getPolicyHolder().getAddress().setNumber(0);
        assertEquals("Address", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullName() {
        policy.getPolicyHolder().setName(null);
        assertEquals("Name", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullFirstName() {
        policy.getPolicyHolder().getName().setFirstName(null);
        assertEquals("Name", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCIEmptyName() {
        policy.getPolicyHolder().getName().setFirstName("   ");
        assertEquals("Name", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullLastName() {
        policy.getPolicyHolder().getName().setLastName(null);
        assertEquals("Name", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCIEmptyLastName() {
        policy.getPolicyHolder().getName().setLastName("    ");
        assertEquals("Name", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullEmail() {
        policy.getPolicyHolder().setEmail(null);
        assertEquals("EmailAddress", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCIEmptyEmail() {
        policy.getPolicyHolder().setEmail("   ");
        assertEquals("EmailAddress", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCIInvalidEmail() {
        policy.getPolicyHolder().setEmail("someemail");
        assertEquals("EmailAddress", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullDriversLicenseNumber() {
        policy.getOperators().get(0).setDriversLicenseNumber(null);
        assertEquals("DriverLicenseNumber", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCIEmptyDriversLicenseNumber() {
        policy.getOperators().get(0).setDriversLicenseNumber("     ");
        assertEquals("DriverLicenseNumber", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCIXDriversLicenseNumber() {
        policy.getOperators().get(0).setDriversLicenseNumber("XXXXX5922");
        assertEquals("DriverLicenseNumber", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCIAstriskDriversLicenseNumber() {
        policy.getOperators().get(0).setDriversLicenseNumber("*****5922");
        assertEquals("DriverLicenseNumber", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullBirthday() {
        policy.getOperators().get(0).setBirthdayRange(null);
        assertEquals("ExactBirthDateOfOperator: Caroline White", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullStartPrimaryOperatorBirthday() throws ParseException {
        final BirthdayRange primaryOperatorBirthdayRange = new BirthdayRange();
        primaryOperatorBirthdayRange.setStart(null);
        primaryOperatorBirthdayRange.setEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-22"));
        policy.getOperators().get(0).setBirthdayRange(primaryOperatorBirthdayRange);
        assertEquals("ExactBirthDateOfOperator: Caroline White", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullEndPrimaryOperatorBirthday() throws ParseException {
        final BirthdayRange primaryOperatorBirthdayRange = new BirthdayRange();
        primaryOperatorBirthdayRange.setStart(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-22"));
        primaryOperatorBirthdayRange.setEnd(null);
        policy.getOperators().get(0).setBirthdayRange(primaryOperatorBirthdayRange);
        assertEquals("ExactBirthDateOfOperator: Caroline White", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullStartSecondaryOperatorBirthday() throws ParseException {
        final BirthdayRange primaryOperatorBirthdayRange = new BirthdayRange();
        primaryOperatorBirthdayRange.setStart(null);
        primaryOperatorBirthdayRange.setEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-22"));
        policy.getOperators().get(1).setBirthdayRange(primaryOperatorBirthdayRange);
        assertEquals("ExactBirthDateOfOperator: Mateo Nguyen", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINotExactPrimaryOperatorBirthday() throws ParseException {
        final BirthdayRange primaryOperatorBirthdayRange = new BirthdayRange();
        primaryOperatorBirthdayRange.setStart(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-22"));
        primaryOperatorBirthdayRange.setEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-23"));
        policy.getOperators().get(0).setBirthdayRange(primaryOperatorBirthdayRange);
        assertEquals("ExactBirthDateOfOperator: Caroline White", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINotExactBothOperatorsBirthday() throws ParseException {
        final BirthdayRange primaryOperatorBirthdayRange = new BirthdayRange();
        primaryOperatorBirthdayRange.setStart(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-22"));
        primaryOperatorBirthdayRange.setEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-23"));
        policy.getOperators().get(0).setBirthdayRange(primaryOperatorBirthdayRange);

        final BirthdayRange secondaryOperatorBirthdayRange = new BirthdayRange();
        secondaryOperatorBirthdayRange.setStart(new SimpleDateFormat("yyyy-MM-dd").parse("2020-10-22"));
        secondaryOperatorBirthdayRange.setEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2020-10-23"));
        policy.getOperators().get(1).setBirthdayRange(secondaryOperatorBirthdayRange);
        final List<String> requiredFields = PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance);
        assertEquals("ExactBirthDateOfOperator: Caroline White", requiredFields.get(0));
        assertEquals("ExactBirthDateOfOperator: Mateo Nguyen", requiredFields.get(1));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullPrimaryOperatorGender() {
        policy.getOperators().get(0).setGender(null);
        assertEquals("GenderOfOperator: Caroline White", PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance).get(0));
    }

    @Test
    public void testRetrieveRequiredFieldsTCINullPrimarySecondaryOperatorGenders() {
        policy.getOperators().get(0).setGender(null);
        policy.getOperators().get(1).setGender(null);
        final List<String> requiredFields = PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance);
        assertEquals("GenderOfOperator: Caroline White", requiredFields.get(0));
        assertEquals("GenderOfOperator: Mateo Nguyen", requiredFields.get(1));
    }

    @Test
    public void testRetrieveRequiredFieldsTCIAllRequiredFields() throws ParseException {
        final PolicyHolder policyHolder = policy.getPolicyHolder();
        final List<Operator> operators = policy.getOperators();
        policyHolder.getName().setLastName(null);
        policyHolder.setEmail("someemail");
        policyHolder.getAddress().setCity("");
        operators.get(0).setDriversLicenseNumber("XXXXX34");
        final BirthdayRange primaryOperatorBirthdayRange = new BirthdayRange();
        primaryOperatorBirthdayRange.setStart(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-22"));
        primaryOperatorBirthdayRange.setEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-23"));
        operators.get(0).setBirthdayRange(primaryOperatorBirthdayRange);
        operators.get(1).setGender(null);
        final List<String> requiredFields = PolicyReader.retrieveRequiredFields(policy, InsuranceType.TheColonelInsurance);
        assertEquals("Address", requiredFields.get(0));
        assertEquals("Name", requiredFields.get(1));
        assertEquals("EmailAddress", requiredFields.get(2));
        assertEquals("DriverLicenseNumber", requiredFields.get(3));
        //The last name identifier is null since the operator's last name is null
        assertEquals("ExactBirthDateOfOperator: Caroline null", requiredFields.get(4));
        assertEquals("GenderOfOperator: Mateo Nguyen", requiredFields.get(5));
    }
}