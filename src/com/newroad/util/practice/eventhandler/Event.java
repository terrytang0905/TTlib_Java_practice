package com.newroad.util.practice.eventhandler;



public class Event {

  private EventType type;

  public Event(EventType type) {
    this.type = type;
  }

  public EventType getType() {
    return this.type;
  }
}
