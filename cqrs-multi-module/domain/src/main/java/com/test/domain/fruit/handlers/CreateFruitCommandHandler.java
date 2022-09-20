package com.test.domain.fruit.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.test.domain.fruit.Fruit;
import com.test.domain.fruit.abstractions.FruitRepository;
import com.test.domain.fruit.commands.CreateFruitCommand;
import com.test.domain.fruit.dtos.FruitDto;
import com.test.mediator.abstractions.CommandHandler;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class CreateFruitCommandHandler implements CommandHandler<CreateFruitCommand, Uni<FruitDto>> {

  @Inject
  @Named("fruitRepository")
  FruitRepository fruitRespository;

	@Override
	public Uni<FruitDto> handle(CreateFruitCommand command) {
    Fruit model = new Fruit(command.name());
    return fruitRespository.create(Fruit.toCreateDto(model));
	}

}
