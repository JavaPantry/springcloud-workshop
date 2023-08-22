package com.springcloud.sbsuite.orders.mappers;

import com.springcloud.sbsuite.orders.domain.Customer;
import com.springcloud.sbsuite.dto.CustomerDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, OrderHeaderMapperStruct.class, AddressMapper.class, ContactMapper.class})
public interface CustomerMapper {
	Customer dtoToEntity(CustomerDto customerDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
	CustomerDto entityToDto(Customer customer, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
