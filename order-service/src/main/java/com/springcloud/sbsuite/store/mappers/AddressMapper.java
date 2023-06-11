package com.springcloud.sbsuite.store.mappers;

import com.springcloud.sbsuite.store.domain.Address;
import dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
	Address orderHeaderDtoToOrderLine(AddressDto addressDto);
	AddressDto orderHeaderToOrderHeaderDto(Address address);
}
