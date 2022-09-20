package com.test.domain.fruit.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.test.domain.fruit.dtos.FruitDto;
import com.test.domain.fruit.abstractions.FruitRepository;
import com.test.domain.fruit.queries.GetFruitQuery;
import com.test.mediator.abstractions.QueryHandler;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class GetFruitQueryHandler implements QueryHandler<GetFruitQuery, Uni<FruitDto>> {

  @Inject
  @Named("fruitRepository")
  FruitRepository fruitRespository;

  @Override
  public Uni<FruitDto> handle(GetFruitQuery query) {
    return fruitRespository.get(query.id());
  }
}
