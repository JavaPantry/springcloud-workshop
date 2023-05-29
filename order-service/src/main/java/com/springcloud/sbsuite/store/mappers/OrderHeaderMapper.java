package com.springcloud.sbsuite.store.mappers;

import com.springcloud.sbsuite.store.domain.OrderHeader;
import com.springcloud.sbsuite.store.dto.OrderHeaderDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, OrderLineMapper.class, AddressMapper.class})
public interface OrderHeaderMapper {
	OrderHeader orderHeaderDtoToOrderLine(OrderHeaderDto orderHeaderDto);
	OrderHeaderDto orderHeaderToOrderHeaderDto(OrderHeader orderHeader);
}