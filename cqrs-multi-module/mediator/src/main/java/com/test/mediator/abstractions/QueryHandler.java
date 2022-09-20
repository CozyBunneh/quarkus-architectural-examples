package com.test.mediator.abstractions;

public interface QueryHandler<T extends Query, Response> extends RequestHandler<T, Response> {

  @Override
  public Response handle(T query);
}
