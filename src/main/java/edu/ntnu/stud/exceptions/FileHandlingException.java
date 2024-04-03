package edu.ntnu.stud.exceptions;

public class FileHandlingException extends Exception{
  private static final String ERROR_MESSAGE = "An error occurred while handling the file";

  public FileHandlingException(){
    super(ERROR_MESSAGE);
  }

  public FileHandlingException(String message){
    super(message);
  }
}
