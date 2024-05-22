# IDATT2003 - ChaosGame

## Project description
This is a group project for the course IDATT2003 at NTNU. The project is a ChaosGame application with both a CLI and a GUI. The Application can be used to simulate different fractals.

## JavaDoc
[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://ConradOsvik.github.io/IDATT2003_ChaosGame/javadoc/)

## Project structure
The project has the following structure:
* `src/main/java` - contains the main program
    * `src/main/java/commands` - contains the commands the user can execute
    * `src/main/java/controllers` - contains the controller of the application
    * `src/main/java/enums` - contains the enums used in the application
    * `src/main/java/exceptions` - contains the custom user input exceptions
    * `src/main/java/factory` - contains the factory classes
    * `src/main/java/input` - contains the validated user input
    * `src/main/java/models` - contains the models of the data in the application
    * `src/main/java/utils` - contains utility classes
    * `src/main/java/views` - contains the console view used in the application
* `src/test/java` - contains the tests
    * `src/test/java/input` - contains the tests for the user input
    * `src/test/java/models` - contains the tests for the models

## How to run the application
Before you run the application it can be smart to run `mvn clean install` in the terminal.

To run the application you can simply run the main method in the App class. The application can be run with the argument `terminal` to run the CLI version of the application. If no argument is given the GUI version of the application will be run.

To run the application through the terminal you can run the following command: `mvn clean javafx:run`

## How to run the tests
To run the tests you have multiple options:
- you can run the tests normally through `mvn test`
- you can run the tests clean through `mvn clean test`
- you can run an induvidual test through `mvn -Dtest=TestClassName test`