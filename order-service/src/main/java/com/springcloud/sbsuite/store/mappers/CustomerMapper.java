package com.springcloud.sbsuite.store.mappers;

import com.springcloud.sbsuite.store.domain.Customer;
import com.springcloud.sbsuite.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, OrderHeaderMapper.class, AddressMapper.class, ContactMapper.class})
public interface CustomerMapper {
	Customer customerDtoToCustomer(CustomerDto customerDto);
	CustomerDto customerToCustomerDto(Customer customer);
}
