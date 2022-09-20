package com.test.domain.fruit.handlers;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import com.test.domain.fruit.dtos.FruitDto;
import com.test.domain.fruit.abstractions.FruitRepository;
import com.test.domain.fruit.queries.GetFruitsQuery;
import com.test.mediator.abstractions.QueryHandler;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class GetFruitsQueryHandler implements QueryHandler<GetFruitsQuery, Uni<List<FruitDto>>> {

  @Inject
  @Named("fruitRepository")
  FruitRepository fruitRespository;

	@Override
	public Uni<List<FruitDto>> handle(GetFruitsQuery query) {
    return fruitRespository.get();
	}

}
