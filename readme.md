# Example

Example application to output processed data. To run from command line "java -jar ./dist/example.jar"

## Original prompt
Write a standalone executable or script using the language of your preference (Java is the primary dev language at Expeditors).  Given the provided input data, print the expected output to the console or write to a text file.  Please also include Java unit tests that test your code.

Input data:
"Dave","Smith","123 main st.","seattle","wa","43"
"Alice","Smith","123 Main St.","Seattle","WA","45"
"Bob","Williams","234 2nd Ave.","Tacoma","WA","26"
"Carol","Johnson","234 2nd Ave","Seattle","WA","67"
"Eve","Smith","234 2nd Ave.","Tacoma","WA","25"
"Frank","Jones","234 2nd Ave.","Tacoma","FL","23"
"George","Brown","345 3rd Blvd., Apt. 200","Seattle","WA","18"
"Helen","Brown","345 3rd Blvd. Apt. 200","Seattle","WA","18"
"Ian","Smith","123 main st ","Seattle","Wa","18"
"Jane","Smith","123 Main St.","Seattle","WA","13"

Expected output: 
Each household and number of occupants, followed by:
Each First Name, Last Name, Address and Age sorted by Last Name then First Name where the occupant(s) is older than 18


## Observations and improvements
 * Instructions were a little unclear on sorting and output, I used the format selected
 because it seemed like something that could be useable for people attempting to understand
 how many adults were in each household (possibly for marketing purposes)
 * Household addresses were normalized for better matching purposes
 * The first line of output for each Household is the normalized address for the household,
 This can be compared to each occupants individual address to make sure they were placed in
 the correct location at a glance
