package com.test.mediator;

import java.util.HashMap;
import java.util.HashSet;

import com.test.mediator.abstractions.Mediator;
import com.test.mediator.abstractions.Notification;
import com.test.mediator.abstractions.NotificationHandler;
import com.test.mediator.abstractions.Request;
import com.test.mediator.abstractions.Query;
import com.test.mediator.abstractions.Command;
import com.test.mediator.abstractions.RequestHandler;

@SuppressWarnings("unchecked") // not pretty but the casts require it atm.
public class MediatorImpl implements Mediator {

  private HashMap<String, Object> notificationHandlers = new HashMap<>();
  private HashMap<String, Object> requestHandlers = new HashMap<>();

  @Override
  public <T extends Notification> void registerNotificationHandler(T notificationType,
      NotificationHandler<T> notificationHandler) {
    registerNotificationHandler(getNotificatonNameForNotificationType(notificationType), notificationHandler);
  }

  @Override
  public <T extends Notification> void registerNotificationHandler(String notificationName,
      NotificationHandler<T> notificationHandler) {
    HashSet<Object> handlers = getHandlersForNotification(notificationName);
    handlers.add(notificationHandler);
  }

  @Override
  public <T extends Notification> void unregisterNotficationHandler(T notificationType,
      NotificationHandler<T> notificationHandler) {
    unregisterNotficationHandler(getNotificatonNameForNotificationType(notificationType), notificationHandler);
  }

  @Override
  public <T extends Notification> void unregisterNotficationHandler(String notificationName,
      NotificationHandler<T> notificationHandler) {
    HashSet<Object> handlers = getHandlersForNotification(notificationName);

    // Handlers are empty, nothing to do
    if (!handlers.isEmpty()) {
      return;
    }

    handlers.remove(notificationHandler);
  }

  private <T extends Notification> HashSet<Object> getHandlersForNotification(String notificationName) {
    if (notificationHandlers.containsKey(notificationName)) {
      return (HashSet<Object>) notificationHandlers.get(notificationName);
    } else {
      return new HashSet<Object>();
    }
  }

  private <T extends Notification> String getNotificatonNameForNotificationType(T notificationType) {
    return notificationType.getClass().getSimpleName();
  }

  @Override
  public <T extends Request, R> void registerRequestHandlerForRequest(T requestType,
      RequestHandler<T, R> requestHandler) {
    registerRequestHandlerForRequest(getRequestNameForRequestType(requestType), requestHandler);
  }

  @Override
  public <T extends Request, R> void registerRequestHandlerForRequest(String requestName,
      RequestHandler<T, R> requestHandler) {
    requestHandlers.put(requestName, requestHandler);
  }

  @Override
  public <T extends Request> void unregisterRequestHandlerForRequest(T requestType) {
    unregisterRequestHandlerForRequest(getRequestNameForRequestType(requestType));
  }

  @Override
  public <T extends Request> void unregisterRequestHandlerForRequest(String requestName) {
    requestHandlers.remove(requestName);
  }

  private <T extends Request> String getRequestNameForRequestType(T requestType) {
    return requestType.getClass().getSimpleName();
  }

  @Override
  public <T extends Query, R> R send(T query) {
    return handleSend(query);
  }

  @Override
  public <T extends Command, R> R send(T command) {
    return handleSend(command);
  }

  @Override
  public <T extends Request, R> R send(T request) {
    return handleSend(request);
  }

  private <T extends Request, R> R handleSend(T request) {
    String requestName = getRequestNameForRequestType(request);
    if (!requestHandlers.containsKey(requestName)) {
      return null;
    }

    var requestHandler = (RequestHandler<T, R>) requestHandlers.get(requestName);
    return requestHandler.handle(request);
  }

  @Override
  public <T extends Notification> void notify(T notification) {
    String notificationName = getNotificatonNameForNotificationType(notification);
    HashSet<Object> handlers = getHandlersForNotification(notificationName);

    for (Object handler : handlers) {
      var notificationHandler = (NotificationHandler<T>) handler;
      notificationHandler.handle(notification);
    }
  }
}
