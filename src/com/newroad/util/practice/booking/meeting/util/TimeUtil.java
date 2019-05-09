package com.newroad.util.practice.booking.meeting.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @project AKQACode
 * @version 0.1
 * @author tangzj
 */
public class TimeUtil {

  public enum TimeFormat {
    MEETING_SUBMIT_TIME("yyyy-MM-dd HH:mm:ss"),
    MEETING_TIME("yyyy-MM-dd HH:mm"),
    MEETING_DATE_TIME("yyyy-MM-dd"),
    MEETING_HOUR_TIME("HH:mm"),
    OFFICE_HOUR_TIME("HHmm");

    String format;

    TimeFormat(String format) {
      this.format = format;
    }

    /**
     * @return the format
     */
    public String getFormat() {
      return format;
    }

  }

  /**
   * Parse data time according to SimpleDateFormat.
   * @param timeFormat
   * @param dateTimeStr
   * @return Date
   * @throws ParseException
   */
  public static Date parseDateTime2Date(TimeFormat timeFormat, String dateTimeStr)
      throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat(timeFormat.getFormat());
    return sdf.parse(dateTimeStr);
  }

  /**
   * Format data time according to SimpleDateFormat.
   * @param timeFormat
   * @param dateTime
   * @return String
   */
  public static String formatDateTime2String(TimeFormat timeFormat, Date dateTime) {
    SimpleDateFormat sdf = new SimpleDateFormat(timeFormat.getFormat());
    return sdf.format(dateTime);
  }

  /**
   * Format date time according to SimpleDateFormat.
   * @param timeFormat
   * @param dateTimeLong
   * @return String
   */
  public static String formatDateTime2String(TimeFormat timeFormat, Long dateTimeLong) {
    return formatDateTime2String(timeFormat, new Date(dateTimeLong));
  }

}
