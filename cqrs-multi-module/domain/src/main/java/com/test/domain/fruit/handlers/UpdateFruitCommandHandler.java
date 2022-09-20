package com.test.domain.fruit.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.test.domain.fruit.Fruit;
import com.test.domain.fruit.GlobalId;
import com.test.domain.fruit.abstractions.FruitRepository;
import com.test.domain.fruit.commands.UpdateFruitCommand;
import com.test.domain.fruit.dtos.FruitDto;
import com.test.mediator.abstractions.CommandHandler;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class UpdateFruitCommandHandler implements CommandHandler<UpdateFruitCommand, Uni<FruitDto>> {

  @Inject
  @Named("fruitRepository")
  FruitRepository fruitRespository;

	@Override
	public Uni<FruitDto> handle(UpdateFruitCommand command) {
    Fruit model = new Fruit(new GlobalId(command.id()), command.name());
    return fruitRespository.update(new FruitDto(command.id(), command.name()));
	}

}
