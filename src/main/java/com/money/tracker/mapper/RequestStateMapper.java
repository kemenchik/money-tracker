package com.money.tracker.mapper;

import com.money.tracker.dto.session.CreateAccountSessionState;
import com.money.tracker.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RequestStateMapper {

  @Mapping(target = "externalId", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "transactions", ignore = true)
  @Mapping(target = "order", ignore = true)
  Account stateToDto(CreateAccountSessionState state);
}
