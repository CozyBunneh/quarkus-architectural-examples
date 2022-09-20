package com.test.domain.fruit.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.test.domain.fruit.abstractions.FruitRepository;
import com.test.domain.fruit.commands.DeleteFruitCommand;
import com.test.mediator.abstractions.CommandHandler;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class DeleteFruitCommandHandler implements CommandHandler<DeleteFruitCommand, Uni<Boolean>> {

  @Inject
  @Named("fruitRepository")
  FruitRepository fruitRespository;

	@Override
	public Uni<Boolean> handle(DeleteFruitCommand command) {
    return fruitRespository.delete(command.id());
	}

}
