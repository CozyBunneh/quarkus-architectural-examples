package com.test.domain.fruit;

import java.util.List;
import java.util.stream.Collectors;

import com.test.domain.fruit.dtos.FruitCreateDto;
import com.test.domain.fruit.dtos.FruitDto;

import io.smallrye.mutiny.Uni;

public record Fruit(GlobalId id, String name) {

  public Fruit(GlobalId id, String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("name cannot be null or empty");
    } else if (name.matches(".*\\d.*")) {
      throw new IllegalArgumentException("fruits cannot have numbers in their name");
    }

    this.id = id;
    this.name = name;
  }

  public Fruit(String name) {
    this(null, name);
  }

  public static Uni<List<Fruit>> fromDtos(Uni<List<FruitDto>> dtos) {
    return dtos.map(x -> fromDtos(x));
  }

  public static List<Fruit> fromDtos(List<FruitDto> dtos) {
    return dtos.stream().map(fruit -> fromDto(fruit)).collect(Collectors.toList());
  }

  public static Uni<Fruit> fromDto(Uni<FruitDto> dto) {
    return dto.map(x -> fromDto(x));
  }

  public static Fruit fromDto(FruitDto dto) {
    return new Fruit(new GlobalId(dto.id()), dto.name());
  }

  public static Uni<List<FruitDto>> toDtos(Uni<List<Fruit>> fruits) {
    return fruits.map(x -> toDtos(x));
  }

  public static List<FruitDto> toDtos(List<Fruit> fruits) {
    return fruits.stream().map(fruit -> toDto(fruit)).collect(Collectors.toList());
  }

  public static Uni<FruitDto> toDto(Uni<Fruit> fruit) {
    return fruit.map(x -> toDto(x));
  }

  public static FruitDto toDto(Fruit fruit) {
    return new FruitDto(fruit.id().id(), fruit.name());
  }

  public static FruitCreateDto toCreateDto(Fruit fruit) {
    return new FruitCreateDto(fruit.name());
  }
}
