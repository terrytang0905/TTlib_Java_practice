package com.ttlib.utilities.booking.meeting.service;


/**
 * @project AKQACode
 * @version 0.1
 * @author tangzj
 * @info MeetingReserveServiceIf interface
 */
public interface MeetingReserveServiceIf {

    /**
     * Process meeting requests content and batch schedule meeting calendar.
     * @param meetingReqContent
     * @return String
     */
    public String processMeetingRequests(String meetingReqContent);
    
    /**
     * Get meeting calendar as String format
     * @return String
     */
    public String getMeetingCalendar();
}
