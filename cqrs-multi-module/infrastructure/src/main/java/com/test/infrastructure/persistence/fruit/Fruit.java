package com.test.infrastructure.persistence.fruit;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.test.domain.fruit.dtos.FruitDto;
import com.test.domain.fruit.dtos.FruitCreateDto;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;

@Entity
@Cacheable
public class Fruit extends PanacheEntity {

  @Column(length = 40, unique = true)
  public String name;

  public static Uni<Fruit> create(FruitCreateDto dto) {
    Fruit fruit = Fruit.fromCreateDto(dto);
    return Panache
        .withTransaction(fruit::persist)
        .replaceWith(fruit)
        .ifNoItem()
        .after(Duration.ofMillis(10000))
        .fail()
        .onFailure()
        .transform(t -> new IllegalStateException(t));
  }

  public static Uni<Boolean> delete(Long id) {
    return Panache.withTransaction(() -> deleteById(id));
  }

  public static Uni<List<Fruit>> get() {
    return Fruit
        .listAll(Sort.by("name"));
    // .ifNoItem()
    // .after(Duration.ofMillis(10000))
    // .fail()
    // .onFailure()
    // .recoverWithUni(Uni.createFrom().<List<Fruit>>item(Collections.EMPTY_LIST));
  }

  public static Uni<Fruit> get(Long id) {
    return Fruit.findById(id);
      // .ifNoItem()
      // .after(Duration.ofSeconds(10))
      // .fail()
      // .onFailure(x -> throw new NotFoundException("fruit not found"));
  }

  public static Uni<Fruit> update(FruitDto dto) {
    // return Fruit.stream
    return Panache
        .withTransaction(() -> get(dto.id())
            .onItem().ifNotNull()
            .transform(entity -> {
              entity.name = dto.name();
              return entity;
            })
            .onFailure().recoverWithNull());
  }

  public static Uni<List<FruitDto>> toDtos(Uni<List<Fruit>> fruitsUni) {
    return fruitsUni.map(f -> Fruit.toDtos(f));
  }

  public static Uni<FruitDto> toDto(Uni<Fruit> fruitUni) {
    return fruitUni.map(f -> Fruit.toDto(f));
  }

  public static List<FruitDto> toDtos(List<Fruit> fruits) {
    return fruits.stream().map(f -> Fruit.toDto(f)).collect(Collectors.toList());
  }

  public static FruitDto toDto(Fruit fruit) {
    return new FruitDto(fruit.id, fruit.name);
  }

  public static Fruit fromCreateDto(FruitCreateDto dto) {
    Fruit fruit = new Fruit();
    fruit.name = dto.name();
    return fruit;
  }
}
