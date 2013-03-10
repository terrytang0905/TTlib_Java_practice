package com.ttsoftware.utilities.eventhandler;


import static com.ttsoftware.utilities.eventhandler.EventHub.eventHub;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;



public class LogEventGenerator implements EventGenerator {

  private String log;	
  private transient Logger logger;
  private transient Handler logHandler;

  public LogEventGenerator(String log){
	  this.log=log;
  }
  
  @Override
  public void start() {

    this.logHandler = new LogHandler(log);
    this.logHandler.setLevel(Level.FINE);

    this.logger = Logger.getLogger("com.xhive.index.multipath.merging");
    this.logger.addHandler(this.logHandler);
    this.logger.setLevel(Level.FINE);
  }

  @Override
  public void stop() {
    this.logger.removeHandler(this.logHandler);
  }

  protected class LogHandler extends Handler {
	
	private String keymessage;  
	  
	public LogHandler(String keymessage){
		this.keymessage=keymessage;
	}

    @Override
    public void publish(LogRecord record) {

      String message = record.getMessage();
      if (message.contains(keymessage)) {
        if (message.contains("final")) {
          eventHub().processEvent(new Event(EventType.PARALLER_QUERY));
        } 
      }

    }

    @Override
    public void flush() {
      //
    }

    @Override
    public void close() throws SecurityException {
      //
    }
  }
}
