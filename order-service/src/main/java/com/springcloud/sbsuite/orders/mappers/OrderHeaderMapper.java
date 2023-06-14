package com.springcloud.sbsuite.orders.mappers;

import com.springcloud.sbsuite.orders.domain.OrderHeader;
import com.springcloud.sbsuite.dto.OrderHeaderDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, OrderLineMapper.class, AddressMapper.class})
public interface OrderHeaderMapper {
	OrderHeader orderHeaderDtoToOrderHeader(OrderHeaderDto orderHeaderDto);
	OrderHeaderDto orderHeaderToOrderHeaderDto(OrderHeader orderHeader);
}
