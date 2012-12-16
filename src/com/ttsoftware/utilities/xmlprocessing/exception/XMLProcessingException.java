package com.ttsoftware.utilities.xmlprocessing.exception;

/**
 * A collection of XML navigation utilities for XML processing by DOM4J. 
 * This utilities use D0M4J API to navigate xml documents included insert,update,delete actions.
 * 
 * @author tangz
 * @since 2012
 * @version 0.1
 * 
 */

public class XMLProcessingException extends RuntimeException{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  public XMLProcessingException(String message) {
    super(message);
  }

}
