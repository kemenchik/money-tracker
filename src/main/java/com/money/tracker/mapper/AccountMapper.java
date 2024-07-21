package com.money.tracker.mapper;

import com.money.tracker.dto.AccountDto;
import com.money.tracker.dto.request.CreateAccountRequest;
import com.money.tracker.dto.request.UpdateAccountRequest;
import com.money.tracker.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AccountMapper {

  @Mapping(target = "externalId", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "transactions", ignore = true)
  void update(Account from, @MappingTarget Account to);

  @Mapping(target = "externalId", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "transactions", ignore = true)
  Account requestToModel(CreateAccountRequest request);

  @Mapping(target = "externalId", source = "accountId")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "transactions", ignore = true)
  Account requestToModel(UpdateAccountRequest request);

  @Mapping(target = "id", source = "externalId")
  @Mapping(target = "transactions", ignore = true)
  AccountDto modelToDto(Account model);
}
