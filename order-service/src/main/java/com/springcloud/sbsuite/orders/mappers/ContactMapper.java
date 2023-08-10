package com.springcloud.sbsuite.orders.mappers;

import com.springcloud.sbsuite.orders.domain.Contact;
import com.springcloud.sbsuite.dto.ContactDto;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {
	Contact contactDtoToContact(ContactDto contactDto);
	ContactDto contactToContactDto(Contact contact);
}
