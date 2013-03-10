package com.ttsoftware.utilities.eventhandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class EventHub {

  private static EventHub SINGLETON = new EventHub();

  public static EventHub eventHub() {
    return SINGLETON;
  }

  private Map<EventType, Set<EventListener>> listenerMap;
  private Map<String, EventGenerator> generatorMap;

  private EventHub() {

    this.listenerMap = new HashMap<EventType, Set<EventListener>>();
    this.generatorMap = new HashMap<String, EventGenerator>();
  }

  public void addGenerator(String id, EventGenerator generator) {
    this.generatorMap.put(id, generator);
  }

  public EventGenerator getGenerator(String id) {
    return this.generatorMap.get(id);
  }

  public EventGenerator removeGenerator(String id) {
    return this.generatorMap.remove(id);
  }

  public void addListener(EventType type, EventListener listener) {

    Set<EventListener> listeners = this.listenerMap.get(type);
    if (listeners == null) {
      listeners = new HashSet<EventListener>();
      this.listenerMap.put(type, listeners);
    }
    listeners.add(listener);
  }

  public void removeListener(EventType type, EventListener listener) {

    Set<EventListener> listeners = this.listenerMap.get(type);
    if (listeners != null) {
      listeners.remove(listener);
    }
  }

  public void removeListener(EventListener listener) {

    for (Set<EventListener> listeners : this.listenerMap.values()) {
      listeners.remove(listener);
    }
  }

  public void clear() {
    this.listenerMap.clear();
  }

  public void processEvent(Event event) {

    Set<EventListener> listeners = this.listenerMap.get(event.getType());
    if (listeners != null) {
      for (EventListener listener : listeners) {
        listener.handle(event);
      }
    }
  }
}
