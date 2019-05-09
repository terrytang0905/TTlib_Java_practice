package com.ttlib.utilities.booking.meeting.domain;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.ttlib.utilities.booking.meeting.service.MeetingReserveServiceException;
import com.ttlib.utilities.booking.meeting.util.TimeUtil;
import com.ttlib.utilities.booking.meeting.util.TimeUtil.TimeFormat;

/**
 * @project AKQACode
 * @author tangzj
 * @version 0.1
 * @info
 */
public class MeetingRequest implements Comparable<MeetingRequest> {

  // reqSubmitTimeFormat:yyyy-MM-dd HH:mm:ss
  private Date reqSubmitDateTime;
  private String employeeId;
  // meetingDateFormat:yyyy-MM-dd
  private Date meetingDate;
  // meetingStartTimeFormat:yyyy-MM-dd HH:mm
  private Date meetingStartDateTime;
  // meetingStartTimeFormat:HH:mm
  private int meetingStartTime;
  // meetingEndTimeFormat:yyyy-MM-dd HH:mm
  private Date meetingEndDateTime;
  // meetingStartTimeFormat:HH:mm
  private int meetingEndTime;
  private int meetingDuration;

  /**
   * @param reqSubmitTime
   * @param employeeId
   * @param meetingStartTime
   * @param meetingDuration
   * @throws ParseException
   */
  public MeetingRequest(String reqSubmitTimeStr, String employeeId, String meetingStartTimeStr,
      String meetingDurationStr) {
    super();
    try {
      this.reqSubmitDateTime =
          TimeUtil.parseDateTime2Date(TimeFormat.MEETING_SUBMIT_TIME, reqSubmitTimeStr);
      this.employeeId = employeeId;
      // parse the meeting time using SimpleDateFormat based on the input meetingStartTimeStr parameter.
      this.meetingDate =
          TimeUtil.parseDateTime2Date(TimeFormat.MEETING_DATE_TIME, meetingStartTimeStr);
      this.meetingStartDateTime =
          TimeUtil.parseDateTime2Date(TimeFormat.MEETING_TIME, meetingStartTimeStr);
      this.meetingDuration = Integer.valueOf(meetingDurationStr);
    } catch (ParseException pe) {
      throw new MeetingReserveServiceException("Fail to parse meeting time using the input parameters!",
          pe);
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(meetingStartDateTime);
    this.meetingStartTime = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
    calendar.add(Calendar.HOUR_OF_DAY, meetingDuration);
    this.meetingEndDateTime = calendar.getTime();
    this.meetingEndTime = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
  }

  /**
   * @return the reqSubmitDateTime
   */
  public Date getReqSubmitDateTime() {
    return reqSubmitDateTime;
  }

  /**
   * @return the employeeId
   */
  public String getEmployeeId() {
    return employeeId;
  }

  /**
   * @return the meetingDate
   */
  public Date getMeetingDate() {
    return meetingDate;
  }

  /**
   * @return the meetingStartDateTime
   */
  public Date getMeetingStartDateTime() {
    return meetingStartDateTime;
  }

  /**
   * @return the meetingStartTime
   */
  public int getMeetingStartTime() {
    return meetingStartTime;
  }

  /**
   * @return the meetingEndDateTime
   */
  public Date getMeetingEndDateTime() {
    return meetingEndDateTime;
  }

  /**
   * @return the meetingEndTime
   */
  public int getMeetingEndTime() {
    return meetingEndTime;
  }

  /**
   * @return the meetingDuration
   */
  public int getMeetingDuration() {
    return meetingDuration;
  }

  /**
   * Compare
   * @param other
   */
  @Override
  public int compareTo(MeetingRequest other) {
    if (other == null) throw new IllegalArgumentException();

    if (this.meetingStartDateTime.getTime() > other.meetingStartDateTime.getTime()) {
      return 1;
    } else if (this.meetingStartDateTime.getTime() == other.meetingStartDateTime.getTime()) {
      if (this.reqSubmitDateTime.getTime() == other.reqSubmitDateTime.getTime()) {
        return 0;
      } else {
        return this.reqSubmitDateTime.getTime() > other.reqSubmitDateTime.getTime() ? 1 : -1;
      }
    } else {
      return -1;
    }
  }

  /**
   * Check whether the current MeetingRequest object is overlapped with the input MeetingRquest
   * object.
   * @param other
   * @return
   */
  public boolean isOverlapped(MeetingRequest other) {
    int otherStartTime = other.getMeetingStartTime();
    int otherEndTime = other.getMeetingEndTime();
    if ((this.meetingStartTime <= otherStartTime && this.meetingEndTime > otherStartTime)
        || (this.meetingStartTime < otherEndTime && this.meetingEndTime >= otherEndTime)) {
      return true;
    } else if (this.meetingStartTime >= otherStartTime && this.meetingEndTime <= otherEndTime) {
      return true;
    }
    return false;
  }

}
