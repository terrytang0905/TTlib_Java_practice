package com.newroad.util.practice.booking.meeting.action;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.newroad.util.practice.booking.meeting.service.MeetingReserveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newroad.util.practice.booking.meeting.service.MeetingReserveServiceIf;

/**
 * @project AKQACode
 * @author tangzj
 * @version 0.1
 * @info
 */
public class MainAction {

  private static Logger logger = LoggerFactory.getLogger(MainAction.class);

  /**
   * The main action for reserving meeting schedule.
   * @param args
   */
  public static void main(String[] args) {
    //check the argument valid or not.
    if (args != null && args[0] != null && !args[0].isEmpty()) {
      String inputFilePath = args[0];
      BufferedReader br;
      try {
        //Get the file contents using the input file path.
        br = new BufferedReader(new FileReader(inputFilePath));
        String line = "";
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
          sb.append(line).append("\n");
        }
        String fileContent = sb.toString();
        if (fileContent != null && fileContent != "") {
          MeetingReserveServiceIf reserveService = new MeetingReserveService();
          reserveService.processMeetingRequests(fileContent);
          String calendarInfo = reserveService.getMeetingCalendar();
          System.out.println(calendarInfo);
        }
      } catch (FileNotFoundException e) {
        logger.error("FileNotFoundException:", e);
      } catch (IOException e) {
        logger.error("IOException:", e);
      }
    } else {
      logger.error("Illegal Input Arguments!");
    }
  }

}
