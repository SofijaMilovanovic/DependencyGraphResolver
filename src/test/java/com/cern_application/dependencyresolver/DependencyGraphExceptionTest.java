package com.cern_application.dependencyresolver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DependencyGraphExceptionTest {
  @Test
  public void testExceptionWithMessage() {
    DependencyGraphException exception = new DependencyGraphException("Test message");

    assertEquals("Test message", exception.getMessage());
  }

  @Test
  public void testExceptionWithMessageAndCause() {
    Throwable cause = new Throwable("Cause of the exception");
    DependencyGraphException exception = new DependencyGraphException("Test message", cause);

    assertEquals("Test message", exception.getMessage());
    assertEquals(cause, exception.getCause());
  }
}
