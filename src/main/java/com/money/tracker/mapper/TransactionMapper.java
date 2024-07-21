package com.money.tracker.mapper;

import com.money.tracker.dto.TransactionDto;
import com.money.tracker.dto.request.CreateTransactionRequest;
import com.money.tracker.dto.request.UpdateTransactionRequest;
import com.money.tracker.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TransactionMapper {

  @Mapping(target = "externalId", ignore = true)
  @Mapping(target = "id", ignore = true)
  void update(Transaction from, @MappingTarget Transaction to);

  @Mapping(target = "externalId", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "account", ignore = true)
  Transaction requestToDto(CreateTransactionRequest request);

  @Mapping(target = "externalId", source = "transactionId")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "account", ignore = true)
  Transaction requestToDto(UpdateTransactionRequest request);

  @Mapping(target = "id", source = "externalId")
  @Mapping(target = "account", ignore = true)
  TransactionDto modelToDto(Transaction model);
}
