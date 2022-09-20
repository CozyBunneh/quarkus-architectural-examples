package com.test.api.exception.handler;

import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Provider
public class IllegalArgumentExceptionHandler implements ExceptionMapper<IllegalArgumentException> {

  public Response toResponse(IllegalArgumentException exception) {
    return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), exception.getMessage())
        .entity(exception.getMessage()).build();
  }

}
