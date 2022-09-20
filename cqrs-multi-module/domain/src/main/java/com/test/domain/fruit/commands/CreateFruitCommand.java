package com.test.domain.fruit.commands;

import com.test.mediator.abstractions.Command;

public record CreateFruitCommand(String name) implements Command {
}
