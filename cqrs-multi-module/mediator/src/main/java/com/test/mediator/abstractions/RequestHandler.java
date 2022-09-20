package com.test.mediator.abstractions;

public interface RequestHandler<T extends Request, Response> {

  public Response handle(T request);
}
