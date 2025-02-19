# Athazahra Nabila Ruby - 2306173113

<details>
<Summary><b>Module 1</b></Summary>

## Reflection 1
I have followed clean code principles by keeping the MVC architecture structured and separating concerns between the controller and repository. I used @Autowired for dependency injection to improve modularity and make testing easier. I ensured that method and variable names are clear and consistent to enhance readability. I also implemented UUIDs for product IDs to prevent predictable identifiers, but I realize input validation and error handling need improvement. I plan to add validation and exception handling to make the code more robust.  I believe the code is well-structured, but I can make it more secure and reliable.

## Reflection 2
After writing the unit tests, I feel more confident that the implemented features work correctly, but I also recognize areas for improvement, such as better validation and error handling. The number of unit tests in a class should cover all core functionalities, including both positive and negative scenarios, to ensure reliability. However, achieving 100% code coverage does not mean the code is free from bugs, as logical errors and unexpected inputs may still cause failures.

If I were to create another functional test suite to verify the product list count, I might introduce redundancy by duplicating setup procedures and instance variables. I realize this could reduce code quality, making maintenance harder and increasing inconsistencies. To keep things clean, I should move common setup logic into reusable methods or a base test class, which would help reduce duplication and make the code easier to read.
</details>

<details>
<Summary><b>Module 1</b></Summary>

## Reflection 1
1. There was a couple problem that showed up after I implemented the SonarCloud integration, so I chose the one with the highest severity. The problem was that in my ProductControllerTest there was an empty method that I overlooked when writing the code, specifically the 'SetUp' method. It was thankfully an easy fix, as I just needed to implement creating a new instance of ProductRepository before each test to ensure that each test starts with a fresh instance.
2. I think that my CI/CD workflows has met the continuous integration and continuous deployment. It ensures continous integration because it automatically builds and tests (./gradlew test) on every push and pull request. It also ensures continuous deployment because it automatically pushes the application to Koyeb after a successful build and test phase.

</details>