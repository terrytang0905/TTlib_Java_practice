package com.ttsoftware.utilities.eventhandler;



public class Event {

  private EventType type;

  public Event(EventType type) {
    this.type = type;
  }

  public EventType getType() {
    return this.type;
  }
}
