package com.springcloud.sbsuite.orders.mappers;

import com.springcloud.sbsuite.orders.domain.Address;
import com.springcloud.sbsuite.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
	Address orderHeaderDtoToOrderLine(AddressDto addressDto);
	AddressDto orderHeaderToOrderHeaderDto(Address address);
}
