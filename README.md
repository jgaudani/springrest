# springrest

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
______________________________________________________

A part of the requirement was unclear to me but I have tried to implement what seemed best.
1.) Return the modified JSON object to the main Java class.

I have included compiled 2 files.
1.) Compiled war file
2.) Compressed tar file of code source and pom.xml (Google doesn't let me attach zip file so had to compress it to tar)

The request URL are following for the compiled source:
1.) for Unique count - users/total
2.) for updated JSON - users/update/4
