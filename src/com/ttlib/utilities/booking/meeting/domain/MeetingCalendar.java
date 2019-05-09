package com.ttlib.utilities.booking.meeting.domain;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.ttlib.utilities.booking.meeting.util.TimeUtil;
import com.ttlib.utilities.booking.meeting.util.TimeUtil.TimeFormat;

/**
 * @project AKQACode
 * @author tangzj
 * @version 0.1
 * @info
 */
public class MeetingCalendar {

  private Map<Long, Set<MeetingRequest>> meetingCalendarMap;

  /**
   * @param officeStartTime
   * @param officeCloseTime
   * @param meetingCalendarMap
   */
  public MeetingCalendar() {
    super();
    this.meetingCalendarMap = new TreeMap<Long, Set<MeetingRequest>>();
  }

  /**
   * @param meetingCalendarMap
   */
  public MeetingCalendar(Map<Long, Set<MeetingRequest>> meetingCalendarMap) {
    super();
    this.meetingCalendarMap = meetingCalendarMap;
  }

  /**
   * @return the meetingCalendarMap
   */
  public Map<Long, Set<MeetingRequest>> getMeetingCalendarMap() {
    return meetingCalendarMap;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (meetingCalendarMap != null) {
      for (Map.Entry<Long, Set<MeetingRequest>> entry : meetingCalendarMap.entrySet()) {
        String meetingDateStr =
            TimeUtil.formatDateTime2String(TimeFormat.MEETING_DATE_TIME, entry.getKey());
        sb.append(meetingDateStr).append("\n");
        for (MeetingRequest request : entry.getValue()) {
          String meetingStartTime =
              TimeUtil.formatDateTime2String(TimeFormat.MEETING_HOUR_TIME,
                  request.getMeetingStartDateTime());
          String meetingEndTime =
              TimeUtil.formatDateTime2String(TimeFormat.MEETING_HOUR_TIME,
                  request.getMeetingEndDateTime());
          sb.append(meetingStartTime + " " + meetingEndTime + " ");
          sb.append(request.getEmployeeId()).append("\n");
        }
      }
    }
    return sb.toString();
  }

}
