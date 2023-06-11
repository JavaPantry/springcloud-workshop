package com.springcloud.sbsuite.store.mappers;

import com.springcloud.sbsuite.store.domain.OrderHeader;
import dto.OrderHeaderDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, OrderLineMapper.class, AddressMapper.class})
public interface OrderHeaderMapper {
	OrderHeader orderHeaderDtoToOrderHeader(OrderHeaderDto orderHeaderDto);
	OrderHeaderDto orderHeaderToOrderHeaderDto(OrderHeader orderHeader);
}
