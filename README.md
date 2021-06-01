# missing-field-identification

The primary function relevant to this assignment is "retrieveRequiredFieldsFromPolicyTCI" in 
com.mfi.trellis.logic.PolicyReader.java which takes in a policy, evaluates it and returns
the required fields. 
It was originally a general required fields reader, but its signature has been modified to serve
only The Colonel Insurance.

To Run:
1) Double-click on the "missing-field-identification.jar" in the root folder.
   OR
1) Open terminal, go to the root of the project where the jar is and run this command without quotes
   "java -jar missing-field-identification.jar".
   OR
1) Open project in Intellij and run the Main function.
2) You should see the required fields in the console output.
2) The function reads from policies.json file on the same level as the source folder/jar. If
   you want to run with a different set of data; this is the location where the json file
   needs to be stored.
3) I have set the iterator in the main function to read policies from this file,
   so if the data is different, the iterator will need to be changed.

Considerations:
1) Since we have a switch case and enum type for different insurance types, we can re-use the
   various check functions and create one for Rancher's like retrieveRequiredFieldsFromPolicyRanchers
2) Ideally, I would move out the methods per policy into a different class per insurance.
   These classes would be under a new "com.mfi.trellis.insurance" package.

How I approached the problem:
1) Understand requirements.
2) Create models based on data definition.
3) Create test for Policy class as an example of getters and setters testing.
4) Create a class to store required fields per insurance.
5) Create functions for each required field.
6) Use these functions in a singular function that ties them together.
7) White box solution with provided json data to ensure correctness and make improvements as required.
8) Update method in account for Ranchers insurance consideration.

What improvements can be made:
1) Be more specific about exactly which fields are missing from the Address and the Name section.
   Currently, the output is insinuating yes, the address and name are required and incorrect,
   but it can be improved to detail if it is the street, city, first name, last name, etc. that 
   are missing from those types.
2) More testing for getters and setters. I Have created skeletons for them, but in general since getters and setters 
   will always pass, I decided to spend my time on logic instead. However, I did write tests for the "Policy" object as an example.
3) PolicyTermMonths can be an enum since we always know what set of values it will be.
4) Null/Nonnull annotations to method parameters and return types.
5) Make type enum if we knew all Address types in advance.
6) Camelcase sec_unit_* variables in Address class.
   
General notes:
1) Did not add "suffix" or "plus4" as a parameter in the Address object since it is not in the 
   data definition but only on the policies example.
2) No tests written for the main file since it is only for white boxing purposes.