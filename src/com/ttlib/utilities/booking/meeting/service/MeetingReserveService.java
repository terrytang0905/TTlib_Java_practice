package com.ttlib.utilities.booking.meeting.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttlib.utilities.booking.meeting.domain.MeetingCalendar;
import com.ttlib.utilities.booking.meeting.domain.MeetingRequest;
import com.ttlib.utilities.booking.meeting.util.TimeUtil;
import com.ttlib.utilities.booking.meeting.util.TimeUtil.TimeFormat;

/**
 * @project AKQACode
 * @version 0.1
 * @author tangzj
 * @info MeetingReserveService
 */
public class MeetingReserveService implements MeetingReserveServiceIf {

  private static Logger logger = LoggerFactory.getLogger(MeetingReserveService.class);

  private int officeStartTime;

  private int officeCloseTime;

  // meeting requests queue
  private Queue<MeetingRequest> meetingRequests;

  // meeting calendar
  private MeetingCalendar meetingCalendar;

  /**
   * Set the available office startTime and closeTime according to input text.
   * @param officeStartTime
   * @param officeCloseTime
   */
  public void setOfficeHour(String officeStartTime, String officeCloseTime) {
    try {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(TimeUtil.parseDateTime2Date(TimeFormat.OFFICE_HOUR_TIME, officeStartTime));
      this.officeStartTime =
          calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);

      calendar.setTime(TimeUtil.parseDateTime2Date(TimeFormat.OFFICE_HOUR_TIME, officeCloseTime));
      this.officeCloseTime =
          calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
    } catch (ParseException pe) {
      logger.error("Fail to parse office hour time using the input parameters!", pe);
      throw new MeetingReserveServiceException(
          "Fail to parse office hour time using the input parameters!", pe);
    }
  }

  @Override
  public String processMeetingRequests(String meetingReqContent) {
    String[] requestContentArray = meetingReqContent.split("\n");
    String[] officeHours = requestContentArray[0].split(" ");
    setOfficeHour(officeHours[0], officeHours[1]);

    int arrayNum = requestContentArray.length;
    // the request array number must be odd according to the format in requirement document.
    if (arrayNum % 2 == 0) {
      logger.error("The format input meeting requests exists error!");
      throw new IllegalArgumentException();
    }

    // initialize meeting requests queue and sort queue according the request submit dateTime
    meetingRequests = new PriorityQueue<MeetingRequest>(10, new Comparator<MeetingRequest>() {
      public int compare(MeetingRequest o1, MeetingRequest o2) {
        return o1.getReqSubmitDateTime().getTime() > o2.getReqSubmitDateTime().getTime() ? 1 : -1;
      }
    });

    // submit meeting requests.
    for (int i = 1; i < arrayNum; i = i + 2) {
      String[] lineArray = requestContentArray[i].trim().split(" ");
      String[] lineArray2 = requestContentArray[i + 1].trim().split(" ");
      if (lineArray.length != 3 || lineArray2.length != 3) {
        logger.error("The format input meeting requests exists error!");
        throw new IllegalArgumentException();
      }
      String requestTime = lineArray[0] + " " + lineArray[1];
      String employeeId = lineArray[2];
      String startTime = lineArray2[0] + " " + lineArray2[1];
      String duration = lineArray2[2];
      MeetingRequest meetingReq = new MeetingRequest(requestTime, employeeId, startTime, duration);
      //Check the meeting time available during office hour time.
      if (checkMeetingOfficeHour(meetingReq.getMeetingStartTime(), meetingReq.getMeetingEndTime())) {
        this.meetingRequests.add(meetingReq);
      }
    }
    // batch reserve the available meetings according to meeting requests.
    meetingCalendar = batchScheduleMeeting(meetingRequests);
    return getMeetingCalendar();
  }

  public String getMeetingCalendar() {
    if (meetingCalendar != null) {
      return meetingCalendar.toString();
    }
    return null;
  }

  /**
   * Check whether the conflict is exist between the meetingTime and officeTime.
   * @param meetingStartTime
   * @param meetingEndTime
   * @return
   */
  private boolean checkMeetingOfficeHour(int meetingStartTime, int meetingEndTime) {
    boolean check = true;
    if (meetingStartTime >= meetingEndTime) {
      check = false;
    } else if (meetingStartTime < this.officeStartTime || meetingEndTime >= this.officeCloseTime) {
      check = false;
    }
    return check;
  }

  /**
   * Batch process meeting requests queue and generate meeting calendar.
   */
  public MeetingCalendar batchScheduleMeeting(Queue<MeetingRequest> meetingRequests) {
    MeetingCalendar meetingCalendar = null;
    Map<Long, Set<MeetingRequest>> meetingCalendarMap = null;
    if (meetingRequests != null && meetingRequests.size() > 0) {
      meetingCalendarMap = new TreeMap<Long, Set<MeetingRequest>>();
      while (meetingRequests.peek() != null) {
        MeetingRequest request = meetingRequests.poll();
        long meetingDate = request.getMeetingDate().getTime();
        Set<MeetingRequest> checkedMeetingSet = meetingCalendarMap.get(meetingDate);
        if (checkedMeetingSet == null) {
          checkedMeetingSet = new TreeSet<MeetingRequest>();
          checkedMeetingSet.add(request);
          meetingCalendarMap.put(meetingDate, checkedMeetingSet);
        } else {
          if (!checkMeetingOverlapped(request, checkedMeetingSet)) {
            checkedMeetingSet.add(request);
            meetingCalendarMap.put(meetingDate, checkedMeetingSet);
          }
        }
      }
    }
    if (meetingCalendarMap != null && meetingCalendarMap.size() > 0) {
      meetingCalendar = new MeetingCalendar(meetingCalendarMap);
    }
    return meetingCalendar;
  }

  /**
   * Check the meeting request overlap with the current checked meeting set.
   * @param meetingReq
   * @param checkedMeetingSet
   * @return
   */
  private boolean checkMeetingOverlapped(MeetingRequest meetingReq,
      Set<MeetingRequest> checkedMeetingSet) {
    boolean isOverlapped = false;
    for (MeetingRequest checkedMeeting : checkedMeetingSet) {
      if (meetingReq.isOverlapped(checkedMeeting)) {
        isOverlapped = true;
        break;
      }
    }
    return isOverlapped;
  }

}
