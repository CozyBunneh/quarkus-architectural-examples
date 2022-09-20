package com.test.infrastructure.persistence.fruit;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.test.domain.fruit.dtos.FruitDto;
import com.test.domain.fruit.dtos.FruitCreateDto;
import com.test.domain.fruit.abstractions.FruitRepository;
import com.test.infrastructure.persistence.fruit.Fruit;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
@Named("fruitRepository")
public class FruitRepositoryImpl implements FruitRepository {

  @Override
  public Uni<FruitDto> create(FruitCreateDto dto) {
    return Fruit.toDto(Fruit.create(dto));
  }

  @Override
  public Uni<Boolean> delete(Long id) {
    return Fruit.delete(id);
  }

	@Override
	public Uni<List<FruitDto>> get() {
	    return Fruit.toDtos(Fruit.get());
	}
	
	@Override
	public Uni<FruitDto> get(Long id) {
	    return Fruit.toDto(Fruit.get(id));
	}

  @Override
  public Uni<FruitDto> update(FruitDto dto) {
    return Fruit.toDto(Fruit.update(dto));
  }
}
