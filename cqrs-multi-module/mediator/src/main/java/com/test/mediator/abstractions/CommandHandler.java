package com.test.mediator.abstractions;

public interface CommandHandler<T extends Command, Response> extends RequestHandler<T, Response> {

  @Override
  public Response handle(T command);
}
