# springrest

Take home story:

"Using the following JSON feed: http://jsonplaceholder.typicode.com/posts"

Create REST endpoints that reads the above JSON feed using HTTP.  The service should perform the following tasks:

Count endpoint

Tally the number of unique user Ids in the JSON and return in a JSON response.

Updated User List endpoint

Modify the 4th JSON array item, changing the title and body of the object .

Return the modified JSON object to the main Java class.

Return the modified JSON in the endpoint response.

Unit tests

Should be runnable via "mvn test"

Should be written to test all aspects of the application (include mock of the feed).

Requirements

Use Java 1.8+

Use Spring Boot

Use Maven

Use JUnit and Mockito or other mock testing framework

All dependencies should be publicly available or properly included with the project and referenced within the POM

Be creative, have fun and may the force be with you
______________________________________________________

A part of the requirement was unclear to me but I have tried to implement what seemed best.
1.) Return the modified JSON object to the main Java class.

I have included compiled 2 files.
1.) Compiled war file
2.) Compressed tar file of code source and pom.xml (Google doesn't let me attach zip file so had to compress it to tar)

The request URL are following for the compiled source:
1.) for Unique count - demo/users/total
2.) for updated JSON - demo/users/update/4
