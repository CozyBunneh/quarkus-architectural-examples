package com.test.domain.fruit;

public record GlobalId(Long id) {

  public GlobalId(Long id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Id cannot be smaller than 0");
    }

    this.id = id;
  }
}
