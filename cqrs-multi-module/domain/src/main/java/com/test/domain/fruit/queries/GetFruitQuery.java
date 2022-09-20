package com.test.domain.fruit.queries;

import com.test.mediator.abstractions.Query;

public record GetFruitQuery(Long id) implements Query {
}
