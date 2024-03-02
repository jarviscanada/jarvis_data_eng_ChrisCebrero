# Introduction
Discuss the design of each app. What does the app do? What technologies have you used? (e.g. core java, libraries, lambda, IDE, docker, etc..)
The application processes files in a specified directory, identifying lines matching a given regex pattern, and saving them to an output file. It utilizes core Java for logic, Maven for dependency management, IntelliJ for development, and Docker for containerization. Lambda functions are employed to streamline line processing. The design ensures efficient file handling and scalability while maintaining code readability and ease of deployment.

# Quick Start
How to use your apps?
In order to use the application, one needs to download the JAR file from the provided source. Execute the JAR via the command line, specifyiing the containing data and the regex pattern. Once processing completes, check the output file for the matched results.

#Implemenation
## Pseudocode
write `process` method pseudocode.
`matchedLines = [];
fileList = listFiles(rootDir)
for file in fileList
  for line in readLines(file)
    if containsPatterns(line)
      matchedLines.add(line)

writeToFile(matchedLines)
`

## Performance Issue
Discuss the memory issue and how would you fix it
The memory issue arises when the Grep App loads the entire file into memory, causing the JVM to run out of memory. To fix it, I would update the readLines interface to use a BufferedReader, and use Stream APIs, enabling the application to read and process the file line by line without exhausting heap space.

# Test
To test the application manually, I first prepared sample data using the provided command, specifying a regex pattern to generate relevant test cases. Then, I executed the Grep Demo JAR file to run the application and generate output based on the sample data. Next, I inspected the application in IntelliJ, focusing on the main function to understand the code logic and execution flow. During this process, I logged variables and method outcomes to trace the execution and identify any potential issues. Finally, I manually inspected the generated output, comparing it against the expected results based on the provided sample data to ensure accuracy and correctness. This systematic approach to manual testing helped validate the application's functionality and reliability.

# Deployment
How you dockerize your app for easier distribution?
- Create a Docker Image containing the necessary dependencies and configs
- Create Dockerfile in the project directory
- Build the docker image
- Run the docker container
- Push docker image to Docker Hub

# Improvement
List three things you can improve in this project.
- I  would utilize frameworks such as JUnit to automate testing processes, and to cover edge cases and expected behaviours to ensure reliability
- Implement more robust error handling to prevent unexpected crashes, and provide informative error messages to aid in troubleshooting
- Replace existing data structures with more efficient alternatives (already using techniques such as utilizing streaming data processing)
