package com.springcloud.sbsuite.orders.mappers;

import com.springcloud.sbsuite.orders.domain.OrderLine;
import com.springcloud.sbsuite.dto.OrderLineDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface OrderLineMapper {
	OrderLine orderLineDtoToOrderLine(OrderLineDto orderLineDto);
	OrderLineDto orderLineToOrderLineDto(OrderLine orderLine);
}
