package com.ttlib.utilities.booking.meeting.service;

/**
 * @project AKQACode
 * @version 0.1
 * @author tangzj
 * @info MeetingReserveServiceException
 */
public class MeetingReserveServiceException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String returnMessage;

  public MeetingReserveServiceException() {

  }

  public MeetingReserveServiceException(String massage) {
    this.returnMessage = massage;
  }

  public MeetingReserveServiceException(Throwable e) {
    super(e);
    this.returnMessage = e.getMessage();
  }

  public MeetingReserveServiceException(String message, Throwable e) {
    super(message, e);
    this.returnMessage = message;
  }

  public String getReturnMessage() {
    return returnMessage;
  }

  public void setReturnMessage(String returnMessage) {
    this.returnMessage = returnMessage;
  }
}
