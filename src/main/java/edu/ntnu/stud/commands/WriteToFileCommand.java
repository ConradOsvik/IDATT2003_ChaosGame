package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.CLIController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.models.AffineTransform2D;
import edu.ntnu.stud.models.ChaosGameDescription;
import edu.ntnu.stud.models.ChaosGameFileHandler;
import edu.ntnu.stud.models.Complex;
import edu.ntnu.stud.models.JuliaTransform;
import edu.ntnu.stud.models.Matrix2x2;
import edu.ntnu.stud.models.Transform2D;
import edu.ntnu.stud.models.Vector2D;
import edu.ntnu.stud.views.CLI;
import java.util.ArrayList;
import java.util.List;

public class WriteToFileCommand implements Command{
  private final CLI cli;
  private final ChaosGameFileHandler chaosGameFileHandler;
  private final ValidatedInput validatedInput;
  public WriteToFileCommand(CLI cli, ValidatedInput validatedInput){
    this.cli = cli;
    this.validatedInput = validatedInput;
    this.chaosGameFileHandler = new ChaosGameFileHandler();

  }
  @Override
  public String getName() {
    return "Write to file";
  }

  @Override
  public String getDescription() {
    return "Writes to a file";
  }

  @Override
  public void execute() {
    System.out.println("Executing command: " + getName());
    String fileName = validatedInput.getString("Enter the name of the file to write to: ");
    String path = "src/main/resources/" + fileName + ".txt";

    cli.displayMessage("Enter the values for the min coordinates:");
    double minX0 = validatedInput.getDouble("Enter minX: ");
    double minX1 = validatedInput.getDouble("Enter minY: ");
    cli.displayMessage("Enter the values for the max coordinates:");
    double maxX0 = validatedInput.getDouble("Enter maxX: ");
    double maxX1 = validatedInput.getDouble("Enter maxY: ");

    String transformType = validatedInput.getTransformType("Enter the type of transform to write: ");

    int iterations = validatedInput.getInt("Enter the number of transformations to write: ");
    List<Transform2D> transforms = new ArrayList<>();

    if(transformType.equals("affine")){
      for(int i = 0; i < iterations; i++){
        cli.displayMessage("Enter the values for the affine transformation matrix:");
        double a00 = validatedInput.getDouble("Enter a00: ");
        double a01 = validatedInput.getDouble("Enter a01: ");
        double a10 = validatedInput.getDouble("Enter a10: ");
        double a11 = validatedInput.getDouble("Enter a11: ");

        cli.displayMessage("Enter the values for the affine transformation vector:");
        double x0 = validatedInput.getDouble("Enter x0: ");
        double x1 = validatedInput.getDouble("Enter x1: ");

        transforms.add(new AffineTransform2D(new Matrix2x2(a00, a01, a10, a11), new Vector2D(x0, x1)));
      }
    }
    if(transformType.equals("julia")){
      for(int i = 0; i < iterations; i++){
        cli.displayMessage("Enter the values for the julia complex number: ");
        double real = validatedInput.getDouble("Enter the real part: ");
        double imaginary = validatedInput.getDouble("Enter the imaginary part: ");

        cli.displayMessage("Enter the sign of the imaginary part: ");
        int sign = validatedInput.getSign("Enter the sign: ");

        transforms.add(new JuliaTransform(new Complex(real, imaginary), sign));
      }
    }

    ChaosGameDescription description = new ChaosGameDescription(transforms, new Vector2D(minX0, minX1), new Vector2D(maxX0, maxX1));

    try {
      chaosGameFileHandler.writeToFile(description, path);
    } catch (Exception e) {
      cli.displayErrorMessage("Error writing to file: " + e.getMessage());
    }
  }
}
