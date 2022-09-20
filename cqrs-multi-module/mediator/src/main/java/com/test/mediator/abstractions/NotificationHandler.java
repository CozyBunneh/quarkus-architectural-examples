package com.test.mediator.abstractions;

/**
 * NotificationHandler
 */
public interface NotificationHandler<T extends Notification> {

  public void handle(T notification);
}
