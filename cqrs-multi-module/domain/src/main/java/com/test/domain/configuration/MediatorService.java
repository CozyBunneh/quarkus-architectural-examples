package com.test.domain.configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.test.domain.fruit.commands.CreateFruitCommand;
import com.test.domain.fruit.commands.DeleteFruitCommand;
import com.test.domain.fruit.commands.UpdateFruitCommand;
import com.test.domain.fruit.handlers.CreateFruitCommandHandler;
import com.test.domain.fruit.handlers.DeleteFruitCommandHandler;
import com.test.domain.fruit.handlers.GetFruitQueryHandler;
import com.test.domain.fruit.handlers.GetFruitsQueryHandler;
import com.test.domain.fruit.handlers.UpdateFruitCommandHandler;
import com.test.domain.fruit.queries.GetFruitQuery;
import com.test.domain.fruit.queries.GetFruitsQuery;
import com.test.mediator.MediatorImpl;

@ApplicationScoped
public class MediatorService extends MediatorImpl {

  @Inject
  public MediatorService(CreateFruitCommandHandler createFruitCommandHandler,
      DeleteFruitCommandHandler deleteFruitCommandHandler, GetFruitQueryHandler getFruitQueryHandler,
      GetFruitsQueryHandler getFruitsQueryHandler, UpdateFruitCommandHandler updateFruitCommandHandler) {
    registerRequestHandlerForRequest(CreateFruitCommand.class.getSimpleName(), createFruitCommandHandler);
    registerRequestHandlerForRequest(DeleteFruitCommand.class.getSimpleName(), deleteFruitCommandHandler);
    registerRequestHandlerForRequest(GetFruitQuery.class.getSimpleName(), getFruitQueryHandler);
    registerRequestHandlerForRequest(GetFruitsQuery.class.getSimpleName(), getFruitsQueryHandler);
    registerRequestHandlerForRequest(UpdateFruitCommand.class.getSimpleName(), updateFruitCommandHandler);
  }
}
