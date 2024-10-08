package edu.bu.cs622.utils.observer;

public interface Publisher {

  boolean subscribe(Subscriber s);

  boolean unsubscribe(Subscriber s);

  void notifySubscribers();
}
