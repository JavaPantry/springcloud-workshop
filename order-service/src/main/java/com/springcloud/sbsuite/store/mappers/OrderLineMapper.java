package com.springcloud.sbsuite.store.mappers;

import com.springcloud.sbsuite.store.domain.OrderLine;
import com.springcloud.sbsuite.store.dto.OrderLineDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface OrderLineMapper {
	OrderLine orderLineDtoToOrderLine(OrderLineDto orderLineDto);
	OrderLineDto orderLineToOrderLineDto(OrderLine orderLine);
}
