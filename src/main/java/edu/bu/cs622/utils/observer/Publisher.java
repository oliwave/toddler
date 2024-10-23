package edu.bu.cs622.utils.observer;

/**
 * This is the Publisher class.
 * This class is responsible for representing a Publisher.
 */
public interface Publisher {

  /*
   * Allow Subscriber to register.
   */
  boolean subscribe(Subscriber s);

  /*
   * Allow Subscriber to unregister.
   */
  boolean unsubscribe(Subscriber s);

  /*
   * Notify subscribers when state has changed.
   */
  void notifySubscribers();
}
