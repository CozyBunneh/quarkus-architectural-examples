package com.test.domain.fruit.commands;

import com.test.mediator.abstractions.Command;

public record DeleteFruitCommand(Long id) implements Command {
}
