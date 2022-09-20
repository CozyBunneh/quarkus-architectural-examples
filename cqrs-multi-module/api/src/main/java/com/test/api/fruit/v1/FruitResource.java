package com.test.api.fruit.v1;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.test.api.fruit.v1.contracts.CreateFruitV1;
import com.test.api.fruit.v1.contracts.FruitV1;
import com.test.domain.fruit.commands.CreateFruitCommand;
import com.test.domain.fruit.commands.DeleteFruitCommand;
import com.test.domain.fruit.commands.UpdateFruitCommand;
import com.test.domain.fruit.dtos.FruitDto;
import com.test.domain.configuration.MediatorService;
import com.test.domain.fruit.queries.GetFruitQuery;
import com.test.domain.fruit.queries.GetFruitsQuery;

import io.smallrye.mutiny.Uni;

@Path("/api/v1/fruit")
@ApplicationScoped
public class FruitResource {

  @Inject
  MediatorService mediator;

  @POST
  public Uni<FruitV1> create(CreateFruitV1 fruit) {
    Uni<FruitDto> dto = mediator.send(new CreateFruitCommand(fruit.name()));
    return FruitV1.fromDto(dto);
  }

  @DELETE
  @Path("/{id}")
  public Uni<Boolean> delete(Long id) {
    return mediator.send(new DeleteFruitCommand(id));
  }

  @GET
  public Uni<List<FruitV1>> get() {
    Uni<List<FruitDto>> dtos = mediator.send(new GetFruitsQuery());
    return FruitV1.fromDtos(dtos);
  }

  @GET
  @Path("/{id}")
  public Uni<FruitV1> getSingle(Long id) {
    Uni<FruitDto> dto = mediator.send(new GetFruitQuery(id));
    return FruitV1.fromDto(dto);
  }

  @PUT
  public Uni<FruitV1> update(FruitV1 fruit) {
    Uni<FruitDto> dto = mediator.send(new UpdateFruitCommand(fruit.id(), fruit.name()));
    return FruitV1.fromDto(dto);
  }
}
