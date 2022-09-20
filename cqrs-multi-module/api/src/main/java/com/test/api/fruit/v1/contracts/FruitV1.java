package com.test.api.fruit.v1.contracts;

import java.util.List;
import java.util.stream.Collectors;

import com.test.domain.fruit.dtos.FruitDto;

import io.smallrye.mutiny.Uni;

public record FruitV1(Long id, String name) {
  public static Uni<List<FruitV1>> fromDtos(Uni<List<FruitDto>> dtos) {
    return dtos.map(dto -> FruitV1.fromDtos(dto));
  }

  public static List<FruitV1> fromDtos(List<FruitDto> dtos) {
    return dtos.stream().map(dto -> fromDto(dto)).collect(Collectors.toList());
  }

  public static Uni<FruitV1> fromDto(Uni<FruitDto> dto) {
    return dto.map(x -> FruitV1.fromDto(x));
  }

  public static FruitV1 fromDto(FruitDto dto) {
    return new FruitV1(dto.id(), dto.name());
  }
}
