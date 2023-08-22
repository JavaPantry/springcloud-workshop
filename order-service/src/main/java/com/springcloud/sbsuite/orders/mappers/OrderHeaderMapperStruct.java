package com.springcloud.sbsuite.orders.mappers;

import com.springcloud.sbsuite.orders.domain.OrderHeader;
import com.springcloud.sbsuite.dto.OrderHeaderDto;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class, OrderLineMapper.class, AddressMapper.class})
public interface OrderHeaderMapperStruct {
	@Mapping(target = "orderLines", source = "orderLines")
	OrderHeader dtoToEntity(OrderHeaderDto orderHeaderDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
	@InheritInverseConfiguration
	OrderHeaderDto entityToDto(OrderHeader orderHeader, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
