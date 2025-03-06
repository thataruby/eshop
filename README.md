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
<Summary><b>Module 2</b></Summary>

## Reflection 1
1. There was a couple problem that showed up after I implemented the SonarCloud integration, so I chose the one with the highest severity. The problem was that in my ProductControllerTest there was an empty method that I overlooked when writing the code, specifically the 'SetUp' method. It was thankfully an easy fix, as I just needed to implement creating a new instance of ProductRepository before each test to ensure that each test starts with a fresh instance.
2. I think that my CI/CD workflows has met the continuous integration and continuous deployment. It ensures continous integration because it automatically builds and tests (./gradlew test) on every push and pull request. It also ensures continuous deployment because it automatically pushes the application to Koyeb after a successful build and test phase.

</details>

<details>
<Summary><b>Module 3</b></Summary>

## Reflection 
1. I applied the Single Responsibility Principle (SRP) by putting CarController in a separate file from ProductController, so each one only handles its own job. The Open-Closed Principle (OCP) is also followed by using interfaces for CarService and ProductService, so if I need to add something new, I don’t have to change the existing code. The Dependency Inversion Principle (DIP) is applied because the controllers don’t directly depend on a specific service but use interfaces instead.
2. It makes the project easier to update, test, and expand. Since each class has a clear role (SRP), changes to car-related code won’t break the product features. The OCP makes it easy to add new features without modifying existing classes, reducing the chances of introducing bugs, and the DIP allows us to replace service implementations easily, making testing simpler by using mock versions instead of real database interactions.
3. The project would be messy and hard to update. If both cars and products were handled in one controller (SRP violation), changing one feature could break another. Ignoring OCP means that every time a new feature is added, old code must be changed, which increases the risk of breaking things. Without DIP, the project would be difficult to test because the controllers would be stuck with specific service implementations.
</details>

<details>
<Summary><b>Module 4</b></Summary>

## Reflection 
1. The TDD workflow was helpful because writing tests first made it easier to plan the code and avoid mistakes. According to Percival (2017), a good test should give confidence that the system works, and I think these tests do that because they check different situations, like invalid statuses and empty product lists. However, I realized that some tests could be better, especially in handling duplicate order IDs more clearly. Next time, I will improve my tests by adding more real-world cases and making sure errors are handled properly.
2. The tests mostly follow the F.I.R.S.T. principle: they run Fast, don’t depend on each other (Independent), always give the same results (Repeatable), clearly show if they pass or fail (Self-validating), and were written before the actual code (Timely). But there’s still room to improve, like making sure tests don’t affect each other by properly resetting data before each one. In the future, I will also try to test more edge cases while keeping the tests simple and clear.
</details>