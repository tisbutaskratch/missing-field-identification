package com.mfi.trellis.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfi.trellis.enums.InsuranceType;
import com.mfi.trellis.logic.PolicyReader;
import com.mfi.trellis.models.Policy;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) throws IOException {
        //white box test per policy
        int iterator = 0;
        while (25 != iterator) {
            System.out.println(Arrays.toString(PolicyReader.retrieveRequiredFields(parsePoliciesJsonFile().get(iterator),
                                                                                   InsuranceType.TheColonelInsurance).toArray()));
            iterator++;
        }
    }

    private static List<Policy> parsePoliciesJsonFile() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(Paths.get("policies.json").toFile(), Policy[].class));
    }
}
