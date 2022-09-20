package com.test.domain.fruit.commands;

import com.test.mediator.abstractions.Command;

public record UpdateFruitCommand(Long id, String name) implements Command {
}

