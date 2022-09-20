package com.test.domain.fruit.abstractions;

import java.util.List;

import com.test.domain.fruit.dtos.FruitCreateDto;
import com.test.domain.fruit.dtos.FruitDto;

import io.smallrye.mutiny.Uni;

public interface FruitRepository {
  Uni<FruitDto> create(FruitCreateDto dto);
  Uni<Boolean> delete(Long id);
  Uni<List<FruitDto>> get();
  Uni<FruitDto> get(Long id);
  Uni<FruitDto> update(FruitDto dto);
}
