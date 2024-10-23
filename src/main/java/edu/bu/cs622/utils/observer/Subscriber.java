package edu.bu.cs622.utils.observer;

/**
 * This is the Subscriber class.
 * This class is responsible for representing a Subscriber.
 */
public interface Subscriber {

  /*
   * Get called when Publisher executes the notifySubscribers method.
   */
  void update(Publisher publisher);

}
