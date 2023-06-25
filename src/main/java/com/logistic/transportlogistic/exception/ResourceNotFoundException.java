package com.logistic.transportlogistic.exception;

public class ResourceNotFoundException extends RuntimeException{

  /**
   * Constructs an <code>ResourceNotFoundException</code> with no
   * detail message.
   */
  public ResourceNotFoundException() {
    super();
  }

  /**
   * Constructs an <code>IllegalArgumentException</code> with the
   * specified detail message.
   *
   * @param message the detail message.
   */
  public ResourceNotFoundException(String message) {
    super(message);
  }

  /**
   * Instantiates a new resource not found exception.
   *
   * @param message the exception message
   * @param cause the exception cause
   */
  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new resource not found exception.
   *
   * @param cause the exception cause
   */
  public ResourceNotFoundException(Throwable cause) {
    super(cause);
  }

}
