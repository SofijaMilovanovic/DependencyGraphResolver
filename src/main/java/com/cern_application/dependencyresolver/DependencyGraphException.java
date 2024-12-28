package com.cern_application.dependencyresolver;

/** Thrown to indicate an issue with dependency graph operations. */
public class DependencyGraphException extends RuntimeException {

  /**
   * Constructs a new exception with the specified detail message.
   *
   * @param message the detail message (saved for later retrieval by the {@link #getMessage()}
   *     method)
   */
  public DependencyGraphException(String message) {
    super(message);
  }

  /**
   * Constructs a new exception with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause the cause of the exception
   */
  public DependencyGraphException(String message, Throwable cause) {
    super(message, cause);
  }
}
