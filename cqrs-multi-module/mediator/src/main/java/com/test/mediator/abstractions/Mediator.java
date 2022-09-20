package com.test.mediator.abstractions;

public interface Mediator {

  public <T extends Notification> void registerNotificationHandler(T notificationType, NotificationHandler<T> notificationHandler);
  public <T extends Notification> void registerNotificationHandler(String notificationName, NotificationHandler<T> notificationHandler);
  public <T extends Notification> void unregisterNotficationHandler(T notificationType, NotificationHandler<T> notificationHandler);
  public <T extends Notification> void unregisterNotficationHandler(String notificationName, NotificationHandler<T> notificationHandler);

  public <T extends Request, R> void registerRequestHandlerForRequest(T requestType, RequestHandler<T, R> requestHandler);
  public <T extends Request, R> void registerRequestHandlerForRequest(String requestName, RequestHandler<T, R> requestHandler);
  public <T extends Request> void unregisterRequestHandlerForRequest(T requestType);
  public <T extends Request> void unregisterRequestHandlerForRequest(String requestName);

  public <T extends Request, R> R send(T request);
  public <T extends Query, R> R send(T query);
  public <T extends Command, R> R send(T command);
  public <T extends Notification> void notify(T notification);
}
