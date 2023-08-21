package com.springcloud.sbsuite.orders.mappers;

import com.springcloud.sbsuite.orders.domain.OrderLine;
import com.springcloud.sbsuite.dto.OrderLineDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {DateMapper.class})
public interface OrderLineMapper {

	OrderLineMapper INSTANCE = Mappers.getMapper(OrderLineMapper.class);

	//AVP August 17 @14:15 @Mapping(target = "orderHeader", source = "orderHeader")
	OrderLine dtoToEntity(OrderLineDto orderLineDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
	OrderLineDto entityToDto(OrderLine orderLine, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
