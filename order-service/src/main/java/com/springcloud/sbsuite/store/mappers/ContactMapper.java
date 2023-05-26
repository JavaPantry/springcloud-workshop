package com.springcloud.sbsuite.store.mappers;

import com.springcloud.sbsuite.store.domain.Contact;
import com.springcloud.sbsuite.store.dto.ContactDto;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {
	Contact orderHeaderDtoToOrderLine(ContactDto contactDto);
	ContactDto orderHeaderToOrderHeaderDto(Contact contact);
}
